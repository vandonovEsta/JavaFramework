package api.clients.trello;

import api.clients.BaseClient;
import api.config.AppConfig;
import api.utils.config.ApiKeyAndTokenManager;
import api.utils.config.ConfigLoader;
import api.utils.config.ObjectToMapHelper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class TrelloClient extends BaseClient {

    private Map<String, String> keyAndToken;
    private static TrelloClient instance;
    private RequestSpecification requestSpecification;
    private TrelloClient(String baseUrl) {
        super(baseUrl);
    }

    public static TrelloClient getInstance(String baseUrl){
        if(instance == null){
            instance = new TrelloClient(baseUrl);
        }

        return instance;
    }

    public void setKeyAndToken(Map<String,String> keyAndToken){
        this.keyAndToken = new HashMap<>(keyAndToken);
    }
    public RequestSpecification createRequest(String endpoint, Method method, Map<String, String> headers) {
        return super.createRequest(endpoint, method, headers);
    }

        public <T> RequestSpecification addJsonBody(RequestSpecification request, T body){
        return super.addJsonBody(request, body);
    }

    public Response executeRequest(RequestSpecification request, Method method){
        return super.executeRequest(request, method);
    }

    public Response executeRequestWithLogs(RequestSpecification request, Method method) {
        return super.executeRequestWithLogs(request, method);
    }

    public RequestSpecification addQueryParams(RequestSpecification request, Map<String, String> queryParams) {
        Map<String, String> fullListOfQueryParams = new HashMap<>();
        if(queryParams != null){
            fullListOfQueryParams.putAll(queryParams);
        }
        fullListOfQueryParams.putAll(ApiKeyAndTokenManager.getInstance().getKeyAndToken());
        return super.addQueryParams(request, fullListOfQueryParams);
    }

    public <T> RequestSpecification addQueryParamsFromObject(RequestSpecification request, T obj) {
        Map<String, String> queryParams = ObjectToMapHelper.getInstance().objectToQueryParams(obj);
        return addQueryParams(request, queryParams);
    }

    public  <T> T mapResponse(Response response, Class<T> responseType) {
        return super.mapResponse(response, responseType);
    }
}
