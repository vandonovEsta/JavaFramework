package api.services;

import api.clients.trello.TrelloClient;
import api.config.AppConfig;
import api.models.trello.boards.BoardRequestDto;
import api.models.trello.boards.BoardResponseDto;
import api.utils.config.ObjectToMapHelper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrelloBoardService {
    private String baseUrl;
    private AppConfig config;
    private TrelloClient trelloClient;

    public TrelloBoardService(){
        config = new AppConfig();
        baseUrl = config.getBaseUrl();
        trelloClient = TrelloClient.getInstance(baseUrl);
    }

    //TODO: remove me
    private Map<String, String> getApiKeyAndToken(){
        Map<String, String>map =  new HashMap<>();
        map.put("key", System.getenv("TRELLO_API_KEY"));
        map.put("token", System.getenv("TRELLO_API_TOKEN"));
        return map;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestParams){
        RequestSpecification requestSpecification =  trelloClient.createRequest(config.getBoardsPath(), Method.POST, null);
        Map<String, String> requestQueryParams = new HashMap<>(ObjectToMapHelper.getInstance().objectToQueryParams(requestParams));
        trelloClient.addQueryParams(requestSpecification, requestQueryParams);
        Response response = trelloClient.executeRequestWithLogs(requestSpecification, Method.POST);
        return response.as(BoardResponseDto.class);
    }

    public List<BoardResponseDto> getAllBoards(){
        RequestSpecification requestSpecification = trelloClient.createRequest(config.getMembersPath() +  config.getBoardsPath(), Method.GET, null);
        trelloClient.addQueryParams(requestSpecification,null);
        return trelloClient.executeRequestWithLogs(requestSpecification, Method.GET).as(new TypeRef<List<BoardResponseDto>>() {});

    }

    public void deleteBoard(BoardResponseDto boardsToDelete){
        RequestSpecification requestSpecification = trelloClient.createRequest(config.getBoardsPath() + boardsToDelete.getId() + "/", Method.GET, null);
        trelloClient.addQueryParams(requestSpecification,null);
        trelloClient.executeRequestWithLogs(requestSpecification, Method.DELETE);

    }

    public void deleteBoards(List<BoardResponseDto> boards){
        for (BoardResponseDto board : boards){
            deleteBoard(board);
        }
    }
}
