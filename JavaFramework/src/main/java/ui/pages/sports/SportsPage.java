package ui.pages.sports;

import org.openqa.selenium.By;
import ui.pages.BasePage;
import ui.pages.login.LoginPage;

public class SportsPage extends BasePage {

    public SportsPage(){
        super();
    }

    //Locators:
    By loginButton = By.id("loginButton");
    By balance = By.xpath("//div[@data-testid='user-navigation-trigger-balance']");
    By footballLink = By.xpath("//a[@data-testid='sport-early-nav-link-soccer']");

    public void navigateToPage(){
        driver.navigate().to(appConfig.getUiUrl());
    }

    public LoginPage clickLoginButton(){
        elementFactory.findExtendedElement(loginButton).click();
        return new LoginPage();
    }

    public String getBalance(){
        return elementFactory.findExtendedElement(balance).getText();
    }

    public FootballPage goToFootballSection(){
        elementFactory.findExtendedElement(footballLink).click();
        return new FootballPage();
    }
}
