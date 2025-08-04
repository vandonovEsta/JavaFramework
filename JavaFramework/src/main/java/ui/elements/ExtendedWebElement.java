package ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class ExtendedWebElement {
    private WebElement element;
    private By locator;
    private Logger logger;

    public ExtendedWebElement(WebDriver driver, By locator){
        this.locator = locator;
        //TODO: add wait function
        element = driver.findElement(locator);
        logger = Logger.getLogger(ExtendedWebElement.class.toString());
    }

    public ExtendedWebElement(WebElement element, By locator){
        this.element = element;
        this.locator = locator;
        logger = Logger.getLogger(ExtendedWebElement.class.toString());
    }

    //TODO: handle logging exceptions!
    public void click(){
        logger.info("Attempting to click element with locator " + locator);
        element.click();
    }

    public void sendKeys(String text){
        logger.info("Attempting to send keys to element with locator: " + locator);
        element.sendKeys(text);
    }

    public String getText(){
        logger.info("Attempting to get text from element with locator: " + locator);
        String value = element.getAttribute("value");
        return value != null ? value : element.getText();
    }

    public boolean isVisible(){
        logger.info("Check for visibility element with locator: ");
        return element.isDisplayed();
    }

}
