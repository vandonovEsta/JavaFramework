package api.services;

import api.models.trello.boards.BoardRequestDto;
import api.models.trello.boards.BoardResponseDto;
import api.models.trello.lists.ListResponseDto;
import api.utils.config.ObjectToMapHelper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.constants.HttpStatusCodes.OK;

public class TrelloBoardService extends BaseService {

    public TrelloBoardService() {
        super();
    }

    //TODO: remove me
    private Map<String, String> getApiKeyAndToken() {
        Map<String, String> map = new HashMap<>();
        map.put("key", System.getenv("TRELLO_API_KEY"));
        map.put("token", System.getenv("TRELLO_API_TOKEN"));
        return map;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestParams) {
        String endpoint = endpointBuilder
                .getBoardsPath()
                .buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        Map<String, String> requestQueryParams = new HashMap<>(ObjectToMapHelper.getInstance().objectToQueryParams(requestParams));
        trelloClient.addQueryParams(requestQueryParams);
        Response response = trelloClient.executeRequestWithLogs(Method.POST, OK);
        response.then().statusCode(200); //TODO: move status codes in constant class
        return response.as(BoardResponseDto.class);
    }

    public List<BoardResponseDto> getAllBoards() {
        String endpoint = endpointBuilder
                .getMembersPath()
                .getBoardsPath()
                .buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        trelloClient.addQueryParams(null);
        return trelloClient.executeRequestWithLogs(Method.GET, OK).as(new TypeRef<List<BoardResponseDto>>() {
        });

    }

    public void deleteBoard(BoardResponseDto boardsToDelete) {
        String endpoint = endpointBuilder
                .getBoardsPath()
                .setEndpointValue(boardsToDelete.getId())
                .buildEndpoint() + "/";
        trelloClient.createRequest(endpoint, null);
        trelloClient.addQueryParams(null);
        trelloClient.executeRequestWithLogs(Method.DELETE, OK);

    }

    public void deleteBoards(List<BoardResponseDto> boards) {
        for (BoardResponseDto board : boards) {
            deleteBoard(board);
        }
    }

    public BoardResponseDto getBoard(String boardId) {

        String endpoint = endpointBuilder
                .getBoardsPath()
                .setEndpointValue(boardId)
                .buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        Response response = trelloClient.executeRequestWithLogs(Method.GET, OK);
        return response.as(BoardResponseDto.class);
    }

    public List<ListResponseDto> getListsOnBoard(String boardId) {
        String endpoint = endpointBuilder
                .getBoardsPath()
                .setEndpointValue(boardId)
                .getListsPath()
                .buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        Response response = trelloClient.executeRequestWithLogs(Method.GET, OK);
        return response.as(new TypeRef<List<ListResponseDto>>() {
        });
    }
}
