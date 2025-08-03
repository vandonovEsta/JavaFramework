package api.services;

import api.models.trello.lists.ListRequestDto;
import api.models.trello.lists.ListResponseDto;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static api.constants.HttpStatusCodes.OK;

public class TrelloListService extends BaseService {

    public TrelloListService() {
        super();
    }

    public Response createList(ListRequestDto queryParams) {
        String endpoint = endpointBuilder.getListsPath().buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        trelloClient.addQueryParamsFromObject(queryParams);
        return trelloClient.executeRequestWithLogs(Method.POST, OK
        );
    }

    public ListResponseDto createList(String name, String id) {
        String endpoint = endpointBuilder.getListsPath().buildEndpoint();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("idBoard", id);
        trelloClient.createRequest(endpoint, null);
        trelloClient.addQueryParams(queryParams);
        return trelloClient.executeRequestWithLogs(Method.POST, OK).as(ListResponseDto.class);
    }
}
