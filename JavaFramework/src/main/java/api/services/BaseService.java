package api.services;

import core.config.AppConfig;
import api.utils.config.EndpointBuilder;

public abstract class BaseService {
    protected String baseUrl;
    protected AppConfig config;
    protected EndpointBuilder endpointBuilder;

    public BaseService() {
        config = new AppConfig();
        baseUrl = config.getBaseUrl();
        endpointBuilder = new EndpointBuilder();
    }
}
