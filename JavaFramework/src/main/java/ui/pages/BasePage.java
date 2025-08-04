package ui.pages;

import org.openqa.selenium.WebDriver;
import ui.driver.DriverFactory;

public class BasePage {
    protected WebDriver Driver = DriverFactory.getDriver();
}
