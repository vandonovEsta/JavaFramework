package ui.models.filter;

import ui.models.LeagueEventDto;
import ui.models.LeagueItemDto;
import ui.models.LeagueListDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LeagueManager {

    public static LeagueListDto filterLeagues(LeagueListDto input, double minOdd, double maxOdd) {
        List<LeagueItemDto> filteredItems = input.getLeagueItems().stream()
                .filter(item -> item.getLeagueEvents() != null && item.getLeagueEvents().size() > 1)
                .map(item -> new LeagueItemDto(
                        item.getName(),
                        filterEventsByOdds(item.getLeagueEvents(), minOdd, maxOdd)
                ))
                .filter(item -> item.getLeagueEvents() != null && !item.getLeagueEvents().isEmpty())
                .collect(Collectors.toList());

        return new LeagueListDto(filteredItems);
    }

    private static List<LeagueEventDto> filterEventsByOdds(List<LeagueEventDto> events, double min, double max) {
        return events.stream()
                .filter(event -> event.getOdds() != null && event.getOdds().stream().allMatch(oddStr -> {
                    try {
                        double odd = Double.parseDouble(oddStr);
                        return odd > min && odd <= max;
                    } catch (NumberFormatException e) {
                        return false; // This will skip if we accidentally catch other elements
                    }
                }))
                .collect(Collectors.toList());
    }

    public static List<LeagueItemDto> filterLeaguesWithMultipleEvents(LeagueListDto leagueListDto) {
        return leagueListDto.getLeagueItems().stream()
                .filter(item -> item.getLeagueEvents() != null && item.getLeagueEvents().size() > 1)
                .collect(Collectors.toList());
    }

    public static List<LeagueEventDto> getAllEvents(LeagueListDto leagueListDto) {

        return leagueListDto.getLeagueItems().stream()
                .filter(item -> item.getLeagueEvents() != null)
                .flatMap(item -> item.getLeagueEvents().stream())
                .collect(Collectors.toList());
    }

    public static void printAllEvents(LeagueListDto leagueListDto) {

        leagueListDto.getLeagueItems().stream()
                .filter(item -> item.getLeagueEvents() != null)
                .flatMap(item -> item.getLeagueEvents().stream())
                .forEach(event -> System.out.println(event.toString()));
    }

    public static List<Double> getOdds(LeagueListDto list){
        return list.getLeagueItems().stream()
                .flatMap(item -> item.getLeagueEvents().stream())
                .flatMap(event -> event.getOdds().stream())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
