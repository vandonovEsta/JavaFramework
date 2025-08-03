package api.models.trello.actions;

import api.models.trello.boards.BoardResponseDto;
import api.models.trello.cards.CardResponseDto;
import api.models.trello.lists.ListResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionDataDto {
    private String text;
    private CardResponseDto card;
    private BoardResponseDto board;
    private ListResponseDto list;
}
