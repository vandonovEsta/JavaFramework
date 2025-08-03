package api.models.trello.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionResponseDto {
    private String id;
    private String idMemberCreator;
    private ActionDataDto data;
    private String type;
    private String date;
}
