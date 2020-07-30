package testcases;

import config.Config;
import drivermanager.DriverManager;
import listeners.ScreenshotListenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Utils;

import javax.rmi.CORBA.Util;
import javax.swing.text.Utilities;
import java.io.IOException;
import java.util.Date;

@Listeners(ScreenshotListenter.class)
public class HomeTest {

    private static Logger logger = LogManager.getLogger(HomeTest.class);

    @Test
    public void dummyTest() {
        logger.fatal("Here is I am with {} FATAL ", "Ayush");
        logger.info("Here is I am with {} INFO ", "Ayush");
        logger.error("Here is I am with {} ERROR ", "Ayush");
        logger.debug("Here is I am with {} DEBUG ", "Ayush");
        logger.trace("Here is I am with {} DEBUG ", "Ayush");
    }

    @Test
    public void testBroswerProperty() throws InterruptedException, IOException {
        WebDriver driver = DriverManager.getDriver();
        driver.get(Config.getProperty("app.url"));
        Assert.assertEquals(driver.getTitle(), "Facebook");
        Thread.sleep(5000);
    }

    @AfterSuite
    public void tearDown() {
        DriverManager.getDriver().quit();
    }
}
