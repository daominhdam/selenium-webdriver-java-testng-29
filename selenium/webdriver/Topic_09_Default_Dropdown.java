package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String firstName = "Kevin", lastName = "Lamping", emailAddress = getEmailAddress();
    String companyName = "Selenium WebDriver", password = "123456";
    String day = "15", month = "November", year = "1950";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        // Day Dropdown
        Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));

        // Chọn ngày
        dayDropdown.selectByVisibleText(day);

        // Verify dropdown này là Single (ko phải Multiple)
        Assert.assertFalse(dayDropdown.isMultiple());

        // Verify số lượng item trong Dropdown này là 32 item
        Assert.assertEquals(dayDropdown.getOptions().size(), 32);

        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");

        // Login
        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(2);

        // Verify
        driver.findElement(By.className("ico-account")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress() {
        Random rand = new Random();
        return "kevinlamp" + rand.nextInt(99999) + "@gmail.net";
    }
}