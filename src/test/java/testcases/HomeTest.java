package testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import config.Config;
import data.ExcelDataManager;
import drivermanager.DriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
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
import reports.HtmlReports;
import utils.Utils;




@Listeners(ScreenshotListenter.class)
public class HomeTest {

    private static Logger logger = LogManager.getLogger(HomeTest.class);
    WebDriver driver;
    MainNavBar mainNavBar;
    ContactSalesPage salesPage;


    @BeforeSuite
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Config.getProperty("app.url"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("truste-consent-button"))).click();

    }

    @Test(enabled = true)
    public void testMeetingChat() {
        ExtentTest test = HtmlReports.createTest("testMeetingChat");
        test.log(Status.INFO, "Initialized Menu bar");
        mainNavBar = new MainNavBar(driver);
        test.log(Status.INFO, "Clicked meeting");
        mainNavBar.clickOnMeeting();

        try {
            Assert.assertEquals(driver.getTitle(), "Zoom Meetings - Zoom");
            test.log(Status.PASS, "Title Matches");
        }catch (Exception ex){
            test.log(Status.FAIL, "Title Does't Matches");
            throw ex;
        }

    }

    @Test(dataProvider = "contactProvider2", dataProviderClass = ExcelDataManager.class, enabled = false)
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

//    @Test
//    public void getprogramList() {
//        Response response = RestAssured.get("https://pragra.io/api/course-info/{id}", "5f0b1d8216b5b97e87391483");
//        String s = response.body().prettyPrint();
//
//      ValidatableResponse res = RestAssured.when()
//                .get("https://pragra.io/api/course-info/{id}", "5f0b1d8216b5b97e87391483")
//                .then();
//      res.assertThat().statusCode(400);
//      //res.body("courseCode", isEquals("DATA-SCIENCE"));
//
//    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        HtmlReports.flush();
        DriverManager.getDriver().quit();
    }
}
