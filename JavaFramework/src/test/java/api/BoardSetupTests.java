package api;

import api.models.trello.boards.BoardRequestDto;
import api.models.trello.boards.BoardResponseDto;
import api.models.trello.lists.ListResponseDto;
import api.services.TrelloBoardService;
import api.services.TrelloListService;
import org.testng.annotations.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class BoardSetupTests extends BaseTest {

    protected String boardId;
    protected String toDoListId;
    protected String inProgressListId;
    protected String doneListid;

    @Test
    public void boardWorkflowTest() {
        //Variables
        String boardName = "Interview Board";
        String toDo = "To Do";
        String inProgress = "In Progress";
        String done = "Done";

        TrelloBoardService trelloBoardService = new TrelloBoardService();
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setName(boardName);

        //Create Board
        BoardResponseDto boardResponseDto = trelloBoardService.createBoard(boardRequestDto);
        boardId = boardResponseDto.getId();
        TrelloListService trelloListService = new TrelloListService();

        //Create Lists
        //TODO:manage positions so lists are ordered correctly!! the current order is a workaround
        ListResponseDto doneList = trelloListService.createList(done, boardResponseDto.getId());
        doneListid = doneList.getId();
        ListResponseDto inProgressList = trelloListService.createList(inProgress, boardResponseDto.getId());
        inProgressListId = inProgressList.getId();
        ListResponseDto todoList = trelloListService.createList(toDo, boardResponseDto.getId());
        toDoListId = todoList.getId();


        //Assert lists in board
        List<ListResponseDto> allListsForBoard = trelloBoardService.getListsOnBoard(boardResponseDto.getId());
        assertTrue(todoList + " not in :/n " + allListsForBoard, allListsForBoard.contains(todoList));
        assertTrue(inProgressList + " not in :/n " + allListsForBoard, allListsForBoard.contains(inProgressList));
        assertTrue(doneList + " not in :/n " + allListsForBoard, allListsForBoard.contains(doneList));
    }
}
