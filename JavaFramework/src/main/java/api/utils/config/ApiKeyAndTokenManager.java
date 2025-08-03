package api.utils.config;

import java.util.HashMap;
import java.util.Map;

public class ApiKeyAndTokenManager {
    private static ApiKeyAndTokenManager instance;

    private ApiKeyAndTokenManager() {

    }

    public static ApiKeyAndTokenManager getInstance() {
        if (instance == null) {
            instance = new ApiKeyAndTokenManager();
        }
        return instance;
    }

    public Map<String, String> getKeyAndToken() {
        Map<String, String> map = new HashMap<>();
        map.put("key", System.getenv("TRELLO_API_KEY"));
        map.put("token", System.getenv("TRELLO_TOKEN"));
        return map;

    }
}
