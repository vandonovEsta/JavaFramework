package api.models.trello.boards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardResponseDto {
    private String id;
    private String name;
    private String desc;
    private String descData;
    private boolean closed;
    private String idOrganization;
    private String idEnterprise;
    private boolean pinned;
    private String url;
    private String shortUrl;
    private PrefsDto prefs;
    private LabelNamesDto labelNames;
    private LimitsDto limits;
}
