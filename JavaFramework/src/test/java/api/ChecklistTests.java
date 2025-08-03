package api;

import api.models.trello.boards.chekcitems.CheckItemResponseDto;
import api.models.trello.cards.CardResponseDto;
import api.models.trello.checklists.ChecklistResponseDto;
import api.services.CardService;
import api.services.CheckListService;
import api.services.TrelloBoardService;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ChecklistTests extends CardWorkflowTests {

    @BeforeTest
    public void setUp() {
        boardWorkflowTest();
        cardWorkflowTest();
    }

    @Test
    public void checklistTest() {
        String preparationStepsChecklist = "Preparation Steps";
        List<String> preparationStepsChecklistItems = Faker.instance().lorem().words();
        String reviewStepsCheckList = "Review Steps";
        List<String> reviewStepsChecklistItems = Faker.instance().lorem().words();
        TrelloBoardService trelloBoardService = new TrelloBoardService();
        CardService cardService = new CardService();
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
        trelloBoardService.getBoard(boardId);
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

}
