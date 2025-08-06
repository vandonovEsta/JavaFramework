package ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ElementFactory {
    private final WebDriver driver;

    public ElementFactory(WebDriver driver){
        this.driver = driver;
    }

    public ExtendedWebElement findExtendedElement(By locator){
        return new ExtendedWebElement(driver, locator);
    }

    public List<ExtendedWebElement> findAll(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.stream()
                .map(e -> new ExtendedWebElement(driver, locator))
                .collect(Collectors.toList());
    }
}
