package api.models.trello.cards;

import api.models.trello.boards.chekcitems.CheckItemResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardResponseDto {
    private String id;
    private String address;
    //badges object needs implementation
    private List<CheckItemResponseDto> checkItemStates;
    private boolean closed;
    private String coordinates;
    private String creationMethod;
    private String dateLastActivity;
    private String desc;
    private String idList;
    private List<String> idChecklists;
}
