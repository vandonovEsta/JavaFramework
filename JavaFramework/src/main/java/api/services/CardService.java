package api.services;

import api.models.trello.actions.ActionResponseDto;
import api.models.trello.cards.CardResponseDto;
import api.models.trello.checklists.ChecklistResponseDto;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.constants.HttpStatusCodes.OK;

public class CardService extends BaseService {
    public CardService() {
        super();
    }

    public CardResponseDto createCard(String name, String idList) {
        String endpoint = endpointBuilder.getCardsPath().buildEndpoint();

        trelloClient.createRequest(endpoint, null);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("idList", idList);
        trelloClient.addQueryParams(queryParams);
        return trelloClient.executeRequestWithLogs(Method.POST, OK).as(CardResponseDto.class);
    }

    public CardResponseDto mobeCard(String id, String idList) {
        String endpoint = endpointBuilder.getCardsPath().setEndpointValue(id).buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idList", idList);
        trelloClient.addQueryParams(queryParams);
        return trelloClient.executeRequestWithLogs(Method.PUT, OK).as(CardResponseDto.class);
    }

    public CardResponseDto getCard(String id) {
        String endpoint = endpointBuilder.getCardsPath().setEndpointValue(id).buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        return trelloClient.executeRequestWithLogs(Method.GET, OK).as(CardResponseDto.class);
    }

    public Response addCommentToCard(String id, String comment) {
        String endpoint = endpointBuilder
                .getCardsPath()
                .setEndpointValue(id)
                .getActionsPath()
                .getCommentsPath()
                .buildEndpoint();

        trelloClient.createRequest(endpoint, null);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("text", comment);
        trelloClient.addQueryParams(queryParams);
        return trelloClient.executeRequestWithLogs(Method.POST, OK);
    }

    public List<ActionResponseDto> getActionsForCard(String id) {
        String endpoint = endpointBuilder
                .getCardsPath()
                .setEndpointValue(id)
                .getActionsPath()
                .buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        return trelloClient.executeRequestWithLogs(Method.GET, OK).as(new TypeRef<List<ActionResponseDto>>() {
        });
    }

    public ChecklistResponseDto addCheckListToCard(String id, String name) {
        String endpoint = endpointBuilder
                .getCardsPath()
                .setEndpointValue(id)
                .getCheckListsPath()
                .buildEndpoint();

        trelloClient.createRequest(endpoint, null);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", name);
        trelloClient.addQueryParams(queryParams);
        return trelloClient.executeRequestWithLogs(Method.POST, OK).as(ChecklistResponseDto.class);
    }

    public Response completeCheckItemOnCard(String cardId, String checkItemId) {
        String endpoint = endpointBuilder
                .getCardsPath()
                .setEndpointValue(cardId)
                .getCheckItemPath()
                .setEndpointValue(checkItemId)
                .buildEndpoint();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("state", "complete");
        trelloClient.createRequest(endpoint, null);
        trelloClient.addQueryParams(queryParams);
        return trelloClient.executeRequestWithLogs(Method.PUT, OK);
    }

    public void completeCheckItemsOnCard(String cardId, List<String> checkItemIds) {
        for (String checkItemId : checkItemIds) {
            completeCheckItemOnCard(cardId, checkItemId);
        }
    }

    public List<ChecklistResponseDto> getChecklistsOnCard(String cardId) {
        String endpoint = endpointBuilder
                .getCardsPath()
                .setEndpointValue(cardId)
                .getCheckListsPath()
                .buildEndpoint();
        trelloClient.createRequest(endpoint, null);
        return trelloClient.executeRequestWithLogs(Method.GET, OK).as(new TypeRef<List<ChecklistResponseDto>>() {
        });
    }
}
