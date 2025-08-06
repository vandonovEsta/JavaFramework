package ui.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LeagueEventDto {
    private String startTime;
    private String eventHomeTeam;
    private String eventAwayTeam;
    private List<String> odds;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return eventHomeTeam + " - " + eventAwayTeam + " - " + "(" + String.join(", ", odds) + ")";
    }
}
