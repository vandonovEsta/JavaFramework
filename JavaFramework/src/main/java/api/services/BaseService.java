package api.services;

import api.clients.trello.TrelloClient;
import api.config.AppConfig;
import api.utils.config.EndpointBuilder;

public abstract class BaseService {
    protected String baseUrl;
    protected AppConfig config;
    protected TrelloClient trelloClient;
    protected EndpointBuilder endpointBuilder;

    public BaseService() {
        config = new AppConfig();
        baseUrl = config.getBaseUrl();
        trelloClient = TrelloClient.getInstance(baseUrl);
        endpointBuilder = new EndpointBuilder();
    }
}
