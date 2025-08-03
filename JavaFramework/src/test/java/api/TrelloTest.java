package api;

import api.models.trello.actions.ActionResponseDto;
import api.models.trello.boards.BoardRequestDto;
import api.models.trello.boards.BoardResponseDto;
import api.models.trello.boards.chekcitems.CheckItemResponseDto;
import api.models.trello.cards.CardResponseDto;
import api.models.trello.checklists.ChecklistResponseDto;
import api.models.trello.lists.ListResponseDto;
import api.services.CardService;
import api.services.CheckListService;
import api.services.TrelloBoardService;
import api.services.TrelloListService;
import com.github.javafaker.Faker;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TrelloTest {
    @Test(priority = 1)
    @Ignore
    public void interviewBoardTest() {
        //TODO:may move them .. also using faker for dto creation
        //Variables
        String boardName = "Interview Board";
        String toDo = "To Do";
        String inProgress = "In Progress";
        String done = "Done";
        String cardName = "Prepare Interview Task";
        String preparationStepsChecklist = "Preparation Steps";
        List<String> preparationStepsChecklistItems = Faker.instance().lorem().words();
        String reviewStepsCheckList = "Review Steps";
        List<String> reviewStepsChecklistItems = Faker.instance().lorem().words();

        TrelloBoardService trelloBoardService = new TrelloBoardService();
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setName(boardName);

        //Create Board
        BoardResponseDto boardResponseDto = trelloBoardService.createBoard(boardRequestDto);
        TrelloListService trelloListService = new TrelloListService();

        //Create Lists
        //TODO:manage positions so lists are ordered correctly!! the current order is a workaround
        ListResponseDto doneList = trelloListService.createList(done, boardResponseDto.getId());
        ListResponseDto inProgressList = trelloListService.createList(inProgress, boardResponseDto.getId());
        ListResponseDto todoList = trelloListService.createList(toDo, boardResponseDto.getId());


        //Assert lists in board
        List<ListResponseDto> allListsForBoard = trelloBoardService.getListsOnBoard(boardResponseDto.getId());
        assertTrue(todoList + " not in :/n " + allListsForBoard, allListsForBoard.contains(todoList));
        assertTrue(inProgressList + " not in :/n " + allListsForBoard, allListsForBoard.contains(inProgressList));
        assertTrue(doneList + " not in :/n " + allListsForBoard, allListsForBoard.contains(doneList));

        //Create card
        CardService cardService = new CardService();
        CardResponseDto card = cardService.createCard(cardName, todoList.getId());
        String cardId = card.getId();
        card = cardService.getCard(cardId);
        assertEquals("Card id is not the same as todoList", todoList.getId(), card.getIdList());

        //Move cart trough lists and validate position
        card = cardService.mobeCard(cardId, inProgressList.getId());
        card = cardService.getCard(cardId);
        assertEquals("Card id is not the same as inProgressList", inProgressList.getId(), card.getIdList());
        card = cardService.mobeCard(cardId, doneList.getId());
        card = cardService.getCard(cardId);
        assertEquals("Card id is not the same as doneList", doneList.getId(), card.getIdList());

        //Add comment and assert actions
        String comment = Faker.instance().lorem().sentence();
        cardService.addCommentToCard(cardId, comment);
        Optional<ActionResponseDto> actionResponseDto = cardService.getActionsForCard(cardId).stream().filter(a -> a.getType().equals("commentCard")).findFirst();
        actionResponseDto.ifPresent(action ->
                assertEquals(comment, action.getData().getText()));

        //Add checklists to card
        CheckListService checkListService = new CheckListService();
        ChecklistResponseDto preparationStepsChecklistDto = cardService.addCheckListToCard(cardId, preparationStepsChecklist);
        checkListService.createCheckItemsOnChecklist(preparationStepsChecklistDto.getId(), preparationStepsChecklistItems);
        ChecklistResponseDto reviewStepsChecklistDto = cardService.addCheckListToCard(cardId, reviewStepsCheckList);
        checkListService.createCheckItemsOnChecklist(reviewStepsChecklistDto.getId(), reviewStepsChecklistItems);
        List<String> checkItemIds = checkListService.getCheckItemsOnChecklist(preparationStepsChecklistDto.getId()).stream().map(CheckItemResponseDto::getId).collect(Collectors.toList());
        //Mark Preparation Steps as complete
        cardService.completeCheckItemsOnCard(cardId, checkItemIds);
        //Asserts
        CardResponseDto cardData = cardService.getCard(cardId);
        assertTrue(cardData.getIdChecklists().contains(preparationStepsChecklistDto.getId()));
        assertTrue(cardData.getIdChecklists().contains(reviewStepsChecklistDto.getId()));
        trelloBoardService.getBoard(boardResponseDto.getId());
        List<ChecklistResponseDto> checklists = cardService.getChecklistsOnCard(cardId);

        //TODO: move this to a assertion helper class!!!!
        checklists.stream().filter(c -> c.getId().equals(preparationStepsChecklist))
                .forEach(
                        id ->
                                assertEquals("complete", cardData.getCheckItemStates().stream().filter(c -> c.getId()
                                        .equals(id)).findFirst().get().getState())
                );

        checklists.stream().filter(c -> c.getId().equals(reviewStepsCheckList))
                .forEach(
                        id ->
                                assertEquals("incomplete", cardData.getCheckItemStates().stream().filter(c -> c.getId()
                                        .equals(id)).findFirst().get().getState())
                );
    }

    //TODO: set as teardown!

}
