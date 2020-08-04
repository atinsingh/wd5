package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactSalesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //#support_contact>div>div>h1

    @FindBy(xpath = "//div[@id='support_contact']/div/div/h1")
    private WebElement headingH1;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement last_name;

    public ContactSalesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait  =new WebDriverWait(driver, 10);
    }

    public String getHeadingText() {
        return this.headingH1.getText();
    }

    public ContactSalesPage keyInEmail(String email) {
        this.email = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        this.email.sendKeys(email);
        return this;
    }

    public ContactSalesPage keyInCompanyName(String company) {
        this.company.sendKeys(company);
        return this;
    }

    public ContactSalesPage keyFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }

    public ContactSalesPage keyLastName(String lastName) {
        this.last_name.sendKeys(lastName);
        return this;
    }
}
