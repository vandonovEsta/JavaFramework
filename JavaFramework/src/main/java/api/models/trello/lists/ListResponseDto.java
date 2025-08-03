package api.models.trello.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResponseDto {
    private String id;
    private String name;
    private boolean closed;
    private int pos;
    private String softLimit;
    private String idBoard;
    private boolean subscribed;
}
