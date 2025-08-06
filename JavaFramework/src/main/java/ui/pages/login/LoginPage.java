package ui.pages.login;

import org.openqa.selenium.By;
import ui.pages.BasePage;
import ui.pages.accountverification.AccountVerificationPage;


public class LoginPage extends BasePage {

    public LoginPage(){
        super();
    }

    //Locators
    By userNameInput = By.id("login_form[username]");
    By passwordInput = By.id("login-modal-password-input");
    By loginButton = By.xpath("//button[contains(text(), 'Log In')]");

    public AccountVerificationPage login(){
        elementFactory.findExtendedElement(userNameInput).sendKeys(appConfig.getUserName());
        elementFactory.findExtendedElement(passwordInput).sendKeys(appConfig.getPassword());
        elementFactory.findExtendedElement(loginButton).click();

        return new AccountVerificationPage();
    }
}
