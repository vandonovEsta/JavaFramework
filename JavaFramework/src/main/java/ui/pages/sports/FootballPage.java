package ui.pages.sports;

import org.openqa.selenium.By;
import ui.models.mapper.LeagueListMapper;
import ui.models.LeagueListDto;

public class FootballPage extends SportsPage{

    public FootballPage(){
        super();
    }

    By upcomingTab = By.xpath("//div[@data-testid='upcoming']");

    public FootballPage clickUpcoming(){
        elementFactory.findExtendedElement(upcomingTab).click();
        return this;
    }

    public LeagueListDto getLeagueList(){
        return new LeagueListMapper().mapList();
    }
}
