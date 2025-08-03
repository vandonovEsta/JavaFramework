package api.models.trello.checklists;

import api.models.trello.boards.chekcitems.CheckItemResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistResponseDto {
    private String id;
    private String name;
    private String idBoard;
    private String idCard;
    private String pos;
    private List<CheckItemResponseDto> checkItems;
}
