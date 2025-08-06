package ui.models.mapper;

import org.openqa.selenium.By;
import ui.elements.ExtendedWebElement;
import ui.models.LeagueEventDto;

import java.util.List;

public class LeagueEventMapper {

    private ExtendedWebElement itemObject;
    public LeagueEventMapper(ExtendedWebElement itemObject){
        super();
        this.itemObject = itemObject;
    }

    By startTime = By.xpath(".//div[@class = 'start-time']");
    By homeTeam = By.xpath(".//div[contains(@class, 'event-team-home')]/span");
    By awayTeam = By.xpath(".//div[contains(@class, 'event-team-away')]/span");
    By odds = By.xpath(".//div[@class = 'es-market-container market-container market-x12 grid-column-size']" +
            "//div[@class = 'market-odd odd-container']//div[@dir = 'ltr']");

    public LeagueEventDto mapItem(){
        String startTime = itemObject.findExtendedElement(this.startTime).getText();
        String homeTeam = itemObject.findExtendedElement(this.homeTeam).getText();
        String awayTeam = itemObject.findExtendedElement(this.awayTeam).getText();

        List<String> odds = itemObject.getVisibleTextsSafe(this.odds);
        return new LeagueEventDto(startTime, homeTeam, awayTeam, odds);

    }
}
