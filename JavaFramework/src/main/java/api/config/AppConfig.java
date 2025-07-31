package api.config;

import api.utils.config.ConfigLoader;

public class AppConfig {
    private final ConfigLoader config;

    public  AppConfig() {
        config = new ConfigLoader("config.properties");
    }

    public String getBaseUrl(){
        return config.get("baseUrl");
    }

    //Add getters for other configuration properties
}
