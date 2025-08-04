package api.utils.config;

import core.config.AppConfig;

public class EndpointBuilder {
    private final AppConfig config;
    private String endpoint = "";

    public EndpointBuilder() {
        config = new AppConfig();
    }

    /**
     *
     * Helper method to easily buildEndpoints
     *
     */
    ///
    ///
    /// Example
    ///
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
