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

    private DriverManager(String broswerName) {

        if(broswerName.equalsIgnoreCase(BroswerType.CHROME)) {
            System.setProperty(BroswerType.CHROME_PROPERTY, Config.getProperty("chrome.executable"));
            driver = new ChromeDriver();
        }else if (broswerName.equalsIgnoreCase(BroswerType.FIREFOX)) {
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

    public synchronized static WebDriver getDriver(String browserName) {
        if(instance == null || instance.driver  instanceof ChromeDriver && !browserName.equalsIgnoreCase("CHROME")){
             if(instance != null) instance.driver.quit();
            instance  = new DriverManager(browserName);
        }
        return instance.driver;
    }
}
