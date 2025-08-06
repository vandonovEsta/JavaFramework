package ui.models.mapper;

import org.openqa.selenium.By;
import ui.driver.DriverFactory;
import ui.elements.ExtendedWebElement;
import ui.models.LeagueItemDto;
import ui.models.LeagueListDto;

import java.util.ArrayList;
import java.util.List;

public class LeagueListMapper
{
    ExtendedWebElement list;
    By listXpath = By.xpath("//div[@data-testid = 'league-list']");
    By listItems = By.xpath(".//div[@class='league-item']");
    public LeagueListMapper(){
        list = new ExtendedWebElement(DriverFactory.getDriver(), listXpath);
    }

    public LeagueListDto mapList(){
        List<ExtendedWebElement> listItemElements = list.findExtendedElements(listItems);
        List<LeagueItemDto> leagueItems = new ArrayList<>();
        for(ExtendedWebElement element : listItemElements){
            leagueItems.add(new LeagueItemMapper(element).mapItem());
        }

        return new LeagueListDto(leagueItems);
    }
}
