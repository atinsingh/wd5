package drivermanager;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private WebDriver driver;
    private static DriverManager instance;

    private DriverManager() {

        if(Config.getProperty("browser.name").equalsIgnoreCase(BroswerType.CHROME)) {
            System.setProperty(BroswerType.CHROME_PROPERTY, Config.getProperty("chrome.executable"));
            driver = new ChromeDriver();
        }else if (Config.getProperty("browser.name").equalsIgnoreCase(BroswerType.FIREFOX)) {
            System.setProperty(BroswerType.FIREFOX_PROPERTY, Config.getProperty("firefox.executable"));
            driver = new FirefoxDriver();
        }//@Ayush to complete
        // defaulting to
        else{
            System.setProperty(BroswerType.CHROME_PROPERTY, Config.getProperty("chrome.executable"));
            driver = new ChromeDriver();
        }
    }

    public synchronized static WebDriver getDriver() {
        if(instance==null){
            instance  = new DriverManager();
        }
        return instance.driver;
    }
}
