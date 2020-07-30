package testcases;

import config.Config;
import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

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
    public void testBroswerProperty() throws InterruptedException {
        DriverManager.getDriver().get(Config.getProperty("app.url"));
        Thread.sleep(5000);
        DriverManager.getDriver().quit();
    }

}
