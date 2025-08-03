package api.config;

import api.utils.config.ConfigLoader;

public class AppConfig {
    private final ConfigLoader config;

    public AppConfig() {
        config = new ConfigLoader("config.properties");
    }


    public String getBaseUrl() {
        return config.get("baseUrl");
    }

    public String getBoardsPath() {
        return config.get("boardsPath");
    }

    public String getMembersPath() {
        return config.get("membersPath");
    }

    public String getListsPath() {
        return config.get("listsPath");
    }

    public String getCardsPath() {
        return config.get("cardsPath");
    }

    public String getActionsPath() {
        return config.get("actionsPath");
    }

    public String getCommentsPath() {
        return config.get("commentsPath");
    }

    public String getCheckListsPath() {
        return config.get("checkListsPath");
    }

    public String getCheckItemsPath() {
        return config.get("checkItemsPath");
    }

    public String getCheckItemPath() {
        return config.get("checkItemPath");
    }
    //Add getters for other configuration properties
}
