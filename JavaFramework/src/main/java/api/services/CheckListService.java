package api.services;

import api.models.trello.boards.chekcitems.CheckItemResponseDto;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.constants.HttpStatusCodes.OK;

public class CheckListService extends BaseService {

    public CheckListService() {
        super();
    }

    public Response createCheckItemOnChecklist(String id, String name) {

        String endpoint = endpointBuilder
                .getCheckListsPath()
                .setEndpointValue(id)
                .getCheckItemsPath()
                .buildEndpoint();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", name);
        trelloClient.createRequest(endpoint, null);
        trelloClient.addQueryParams(queryParams);
        return trelloClient.executeRequestWithLogs(Method.POST, OK);
    }

    public void createCheckItemsOnChecklist(String id, List<String> names) {
        for (String name : names) {
            createCheckItemOnChecklist(id, name);
        }
    }

    public List<CheckItemResponseDto> getCheckItemsOnChecklist(String id) {
        String endpoint = endpointBuilder
                .getCheckListsPath()
                .setEndpointValue(id)
                .getCheckItemsPath()
                .buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        return trelloClient.executeRequestWithLogs(Method.GET, OK).as(new TypeRef<List<CheckItemResponseDto>>() {
        });
    }
}
