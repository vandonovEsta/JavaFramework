package ui.pages.accountverification;

import org.openqa.selenium.By;
import ui.pages.BasePage;
import ui.pages.sports.SportsPage;

public class AccountVerificationPage extends BasePage {
    By closeWindowButton = By.xpath("//div[h5[@id = 'verifyAccountModal-label']]//button[@class= 'close']");

    public SportsPage closeVerificationPopup(){
        elementFactory.findExtendedElement(closeWindowButton).click();
        return new SportsPage();
    }
}
