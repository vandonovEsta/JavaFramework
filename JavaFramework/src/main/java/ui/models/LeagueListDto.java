package ui.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LeagueListDto {
    private List<LeagueItemDto> leagueItems;
}
