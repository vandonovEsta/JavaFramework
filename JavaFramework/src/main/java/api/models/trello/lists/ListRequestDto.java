package api.models.trello.lists;

import lombok.Data;

@Data
public class ListRequestDto {
    private String name;
    private String idBoard;
    private String idListSource;
}
