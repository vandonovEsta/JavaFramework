package api.models.trello.boards.chekcitems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckItemResponseDto {
    private String id;
    private String name;
    private String state;
    private String dueReminder;
    private String idChecklist;
}
