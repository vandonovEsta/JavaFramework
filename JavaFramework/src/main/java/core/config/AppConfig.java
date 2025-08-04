package core.config;

import api.utils.config.ConfigLoader;

public class AppConfig {
    private final ConfigLoader config;

    public AppConfig() {
        config = new ConfigLoader("config.properties");
    }


    public String getBaseUrl() {
        return config.get("baseUrl");
    }

    public String getUiUrl(){
        return config.get("uiUrl");
    }

    public String getUserName(){
        return config.get("userName");
    }

    public String getPassword(){
        return config.get("password");
    }
}
