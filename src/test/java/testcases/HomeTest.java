package testcases;

import config.Config;
import data.ExcelDataManager;
import drivermanager.DriverManager;
import listeners.ScreenshotListenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ContactSalesPage;
import pages.DataSciencePage;
import pages.MainNavBar;
import pages.TopNavBar;
import utils.Utils;



@Listeners(ScreenshotListenter.class)
public class HomeTest {

    private static Logger logger = LogManager.getLogger(HomeTest.class);
    WebDriver driver = DriverManager.getDriver();
    MainNavBar mainNavBar;
    ContactSalesPage salesPage;


    @BeforeSuite
    public void setUp() {
        driver.get(Config.getProperty("app.url"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("truste-consent-button"))).click();

    }

    @Test(enabled = false)
    public void testMeetingChat() {
        mainNavBar = new MainNavBar(driver);
        mainNavBar.clickOnMeeting();
        Assert.assertEquals(driver.getTitle(), "Zoom Meetings - Zoom");
    }

    @Test(dataProvider = "contactProvider2", dataProviderClass = ExcelDataManager.class)
    public void checkContactSales(String email, String company, String firstName, String lastname){
        mainNavBar = new MainNavBar(driver);
        salesPage = mainNavBar.clickOnContantSales();
        salesPage
                .keyInEmail(email)
                .keyInCompanyName(company)
                .keyFirstName(firstName)
                .keyLastName(lastname);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test(dataProvider = "dummy", dataProviderClass = ExcelDataManager.class)
    public void testDummy(Object ...args) {
        for (Object ob: args){
            System.out.println(ob);
        }
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        DriverManager.getDriver().quit();
    }
}
