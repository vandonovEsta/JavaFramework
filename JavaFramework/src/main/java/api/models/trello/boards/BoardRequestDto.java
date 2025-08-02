package api.models.trello.boards;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    @NotNull
    private String name;
    private boolean defaultLabels;
    private  boolean defaultLists;
    private String desc;
    private String idOrganization;
    private String idBoardSource;
    private String keepFromSource;
    private String powerUps;
    private String prefs_permissionLevel;
    private String prefs_voting;
}
