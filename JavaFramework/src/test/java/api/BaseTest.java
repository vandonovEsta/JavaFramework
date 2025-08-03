package api;

import api.models.trello.boards.BoardResponseDto;
import api.services.TrelloBoardService;
import org.testng.annotations.AfterClass;

import java.util.List;

public class BaseTest {

    @AfterClass
    public void FinalTearDown() {
        TrelloBoardService trelloBoardService = new TrelloBoardService();
        List<BoardResponseDto> responseDtos = trelloBoardService.getAllBoards();
        trelloBoardService.deleteBoards(responseDtos);
        List<BoardResponseDto> boardsAfterDeletion = trelloBoardService.getAllBoards();
        assert boardsAfterDeletion.isEmpty();

    }
}
