package ui.models.mapper;

import org.openqa.selenium.By;
import ui.elements.ExtendedWebElement;
import ui.models.LeagueEventDto;
import ui.models.LeagueItemDto;

import java.util.ArrayList;
import java.util.List;

public class LeagueItemMapper {

    private ExtendedWebElement listObject;
    public LeagueItemMapper(ExtendedWebElement listObject){
        this.listObject = listObject;
    }

    By name = By.xpath(".//span[contains(@class, 'league-title-label')]");
    By eventItems = By.xpath(".//div[@class = 'event event-container']");


    public LeagueItemDto mapItem(){
        String name = listObject.findExtendedElement(this.name).getText();
        List<LeagueEventDto> events = new ArrayList<>();
        for(ExtendedWebElement element : listObject.findExtendedElements(eventItems)){
            events.add(new LeagueEventMapper(element).mapItem());
        }
        return new LeagueItemDto(name, events);
    }

}
