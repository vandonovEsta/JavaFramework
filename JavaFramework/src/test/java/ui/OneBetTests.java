package ui;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import ui.driver.DriverFactory;
import ui.models.LeagueItemDto;
import ui.models.LeagueListDto;
import ui.models.filter.LeagueManager;

import java.util.List;

import static org.testng.Assert.*;

public class OneBetTests extends BaseTest{
    @Test
    public void LeaguesWithMoreThanOneEventTest(){
        LeagueListDto leagueList = page.clickLoginButton()
                .login()
                .closeVerificationPopup()
                .goToFootballSection()
                .clickUpcoming()
                .getLeagueList();

        List<LeagueItemDto> itemsWithMultipleEvents = LeagueManager.filterLeaguesWithMultipleEvents(leagueList);
        LeagueListDto listWithSpecifiedOdds = LeagueManager.filterLeagues(leagueList, 1.50, 4.00);

        System.out.println("Printing leagues with > 1 event");
        itemsWithMultipleEvents.forEach(i -> System.out.println(i.toString()));

        System.out.println("Print league events with specified odds");
        LeagueManager.printAllEvents(listWithSpecifiedOdds);

        itemsWithMultipleEvents.forEach( i ->
                assertTrue(i.getLeagueEvents().size() > 1, "Events for item " + i.getName() + " were not > 1"));

        LeagueManager.getOdds(listWithSpecifiedOdds).forEach(
                e ->
                        assertTrue(e > 1.50 && e <= 4.00, e + " not in range (1.50 - 4.00]")
        );

    }

}
