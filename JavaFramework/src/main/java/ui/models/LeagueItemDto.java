package ui.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LeagueItemDto {
    String name;
    List<LeagueEventDto> leagueEvents;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n");
        if (leagueEvents != null) {
            for (LeagueEventDto event : leagueEvents) {
                sb.append(event.getEventHomeTeam())
                        .append(" - ")
                        .append(event.getEventAwayTeam())
                        .append("\n");
            }
        }
        return sb.toString();
    }
}
