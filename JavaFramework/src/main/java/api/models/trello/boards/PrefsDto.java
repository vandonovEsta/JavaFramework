package api.models.trello.boards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrefsDto {
    private String permissionLevel;
    private boolean hideVotes;
    private String voting;
    private String comments;
    private String invitations;
    private boolean selfJoin;
    private boolean cardCovers;
    private boolean showCompleteStatus;
    private boolean cardCounts;
    @JsonProperty("isTemplate")
    private boolean isTemplate;
    private String cardAging;
    private boolean calendarFeedEnabled;
    private List<HiddenPluginBoardButtons> hiddenPluginBoardButtons;
    private List<SwitcherViewsDto> switcherViews;
    private Object autoArchive;
    private String background;
    private String backgroundColor;
    private String backgroundDarkColor;
    private String backgroundImage;
    private String backgroundDarkImage;
    private Object backgroundImageScaled;
    private boolean backgroundTile;
    private String backgroundBrightness;
    private String sharedSourceUrl;
    private String backgroundBottomColor;
    private String backgroundTopColor;
    private boolean canBePublic;
    private boolean canBeEnterprise;
    private boolean canBeOrg;
    private boolean canBePrivate;
    private boolean canInvite;
}
