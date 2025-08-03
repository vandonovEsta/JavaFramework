package api.utils.config;

import api.config.AppConfig;

public class EndpointBuilder {
    private final AppConfig config;
    private String endpoint = "";

    public EndpointBuilder() {
        config = new AppConfig();
    }

    public EndpointBuilder getBoardsPath() {
        endpoint += config.getBoardsPath();
        return this;
    }

    public EndpointBuilder getMembersPath() {
        endpoint += config.getMembersPath();
        return this;
    }

    public EndpointBuilder getListsPath() {
        endpoint += config.getListsPath();
        return this;
    }

    public EndpointBuilder getCardsPath() {
        endpoint += config.getCardsPath();
        return this;
    }

    public EndpointBuilder getActionsPath() {
        endpoint += config.getActionsPath();
        return this;
    }

    public EndpointBuilder getCommentsPath() {
        endpoint += config.getCommentsPath();
        return this;
    }

    public EndpointBuilder getCheckListsPath() {
        endpoint += config.getCheckListsPath();
        return this;
    }

    public EndpointBuilder getCheckItemsPath() {
        endpoint += config.getCheckItemsPath();
        return this;
    }

    public EndpointBuilder getCheckItemPath() {
        endpoint += config.getCheckItemPath();
        return this;
    }

    public EndpointBuilder setEndpointValue(String value) {
        if (endpoint.charAt(endpoint.length() - 1) == '/') {
            endpoint += value;
        } else {
            endpoint += "/" + value;
        }
        return this;
    }

    public String buildEndpoint() {
        String result = endpoint;
        endpoint = "";
        return result;
    }
}
