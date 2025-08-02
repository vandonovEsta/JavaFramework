package api;

import api.models.trello.boards.BoardRequestDto;
import api.models.trello.boards.BoardResponseDto;
import api.services.TrelloBoardService;
import org.testng.annotations.Test;

import java.util.List;

public class BaseTest {
    @Test
    public void interviewBoardTest(){
        TrelloBoardService trelloBoardService = new TrelloBoardService();
        BoardRequestDto boardDto = new BoardRequestDto();
        boardDto.setName("Interview Board");
        trelloBoardService.createBoard(boardDto);

    }

    @Test
    public void deleteAllBoards(){
        TrelloBoardService trelloBoardService = new TrelloBoardService();
        List<BoardResponseDto> responseDtos = trelloBoardService.getAllBoards();
        trelloBoardService.deleteBoards(responseDtos);
        List<BoardResponseDto> boardsAfterDeletion = trelloBoardService.getAllBoards();
        assert boardsAfterDeletion.isEmpty();
    }
}
