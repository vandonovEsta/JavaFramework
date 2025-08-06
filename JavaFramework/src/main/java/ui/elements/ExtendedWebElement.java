package ui.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.driver.DriverFactory;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ExtendedWebElement {
    private WebElement element;
    private By locator;
    private Logger logger;
    private WebDriverWait wait;

    public ExtendedWebElement(WebDriver driver, By locator){
        this.locator = locator;
        logger = Logger.getLogger(ExtendedWebElement.class.toString());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        setElementWithWait();
    }

    public ExtendedWebElement(WebElement element, By locator){
        this(DriverFactory.getDriver(), locator);
        this.element = element;
    }

    private void setElementWithWait(){
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element found and visible: " + locator.toString());
        } catch (TimeoutException e) {
            logger.severe("Element not visible after waiting: " + locator.toString());
            throw e;
        }
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

    public ExtendedWebElement findExtendedElement(By by){
        logger.info("Finding child element: " + by.toString());
        WebElement child = element.findElement(by);
        return new ExtendedWebElement(child, by);
    }

    public List<ExtendedWebElement> findExtendedElements(By by){
        logger.info("Finding multiple child elements: " + by.toString());
        List<WebElement> children = element.findElements(by);
        return children.stream().map(child -> new ExtendedWebElement(child, by))
                .collect(Collectors.toList());
    }

    public List<String> getVisibleTextsSafe(By locator) {
        int retries = 2;
        while (retries-- > 0) {
            try {
                List<WebElement> elements = element.findElements(locator);
                return elements.stream()
                        .filter(WebElement::isDisplayed)
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
            } catch (StaleElementReferenceException e) {
                // log retry
                logger.warning("Caught stale element, retrying... (" + retries + " retries left)");
            }
        }
        throw new StaleElementReferenceException("Element could not be read after retries");
    }


}
