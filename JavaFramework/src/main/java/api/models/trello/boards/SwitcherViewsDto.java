package api.models.trello.boards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwitcherViewsDto {
    private String viewType;
    private boolean enabled;
}
