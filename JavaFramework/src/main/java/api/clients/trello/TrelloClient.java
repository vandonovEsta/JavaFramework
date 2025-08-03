package api.clients.trello;

import api.clients.BaseClient;
import api.utils.config.ApiKeyAndTokenManager;
import api.utils.config.ObjectToMapHelper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class TrelloClient extends BaseClient {

    private static TrelloClient instance;
    private Map<String, String> keyAndToken;
    private RequestSpecification requestSpecification;

    private TrelloClient(String baseUrl) {
        super(baseUrl);
    }

    public static TrelloClient getInstance(String baseUrl) {
        if (instance == null) {
            instance = new TrelloClient(baseUrl);
        }

        return instance;
    }

    private void setApiKeyAndToken() {
        requestSpecification.queryParams(ApiKeyAndTokenManager.getInstance().getKeyAndToken());
    }

    public RequestSpecification createRequest(String endpoint, Map<String, String> headers) {
        requestSpecification = super.createRequest(endpoint, headers);
        setApiKeyAndToken();
        return requestSpecification;
    }

    public <T> RequestSpecification addJsonBody(RequestSpecification request, T body) {
        super.addJsonBody(requestSpecification, body);
        return requestSpecification;
    }

    public Response executeRequest(RequestSpecification request, Method method) {
        return super.executeRequest(request, method);
    }

    public Response executeRequestWithLogs(Method method, int statusCode) {
        return super.executeRequestWithLogs(requestSpecification, method, statusCode);
    }

    public RequestSpecification addQueryParams(Map<String, String> queryParams) {
        if (queryParams != null) {
            super.addQueryParams(requestSpecification, queryParams);
        }
        return requestSpecification;
    }

    public <T> RequestSpecification addQueryParamsFromObject(T obj) {
        Map<String, String> queryParams = ObjectToMapHelper.getInstance().objectToQueryParams(obj);
        return addQueryParams(requestSpecification, queryParams);
    }

    public <T> T mapResponse(Response response, Class<T> responseType) {
        return super.mapResponse(response, responseType);
    }
}
