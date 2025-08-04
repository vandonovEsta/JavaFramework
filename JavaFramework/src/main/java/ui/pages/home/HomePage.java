package ui.pages.home;

import org.openqa.selenium.By;
import ui.pages.BasePage;

public class HomePage extends BasePage {

    public HomePage(){
        super();
    }

    //Locators:
    By loginButton = By.id("loginButton");


    public void navigateToPage(){
        driver.navigate().to(appConfig.getUiUrl());
    }

}
