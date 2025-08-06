package ui.models.mapper;

import org.openqa.selenium.WebDriver;
import ui.driver.DriverFactory;
import ui.elements.ElementFactory;

public class BaseMapper {
    protected WebDriver driver;
    protected ElementFactory elementFactory;

    protected BaseMapper(){
        driver = DriverFactory.getDriver();
        elementFactory = new ElementFactory(driver);
    }
}
