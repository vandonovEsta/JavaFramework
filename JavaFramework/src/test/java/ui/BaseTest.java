package ui;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.driver.DriverFactory;
import ui.pages.sports.SportsPage;

public class BaseTest {
    //TODO: add general logic for each test
    //TODO: Screenshots in teardowns

    protected SportsPage page;

    @BeforeTest
    public void init(){
        page = new SportsPage();
        page.navigateToPage();
    }

    @AfterTest
    public void TearDown(){
        DriverFactory.quitDriver();
    }
}
