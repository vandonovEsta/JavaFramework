package ui.pages;

import core.config.AppConfig;
import org.openqa.selenium.WebDriver;
import ui.driver.DriverFactory;

public class BasePage {
    protected WebDriver driver;
    protected AppConfig appConfig;

    protected BasePage(){
        driver = DriverFactory.getDriver();
        appConfig = new AppConfig();
    }
}
