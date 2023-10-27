package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));

        // Verify button bị disable khi chưa click vào checkbox
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSeconds(2);

        // Verify button đã được enable sau khi click vào checkbox
        Assert.assertTrue(registerButton.isEnabled());

        // Lấy ra mã màu nền của button
        String registerBackgroundRGB = registerButton.getCssValue("background-color");

        // Convert từ kiểu String (mã RGB) qua kiểu Color
        Color registerBackgroundColour = Color.fromString(registerBackgroundRGB);

        // Convert qua kiểu Hexa
        String registerBackgroundHexa = registerBackgroundColour.asHex();

        // Convert qua viết thường
        registerBackgroundHexa = registerBackgroundHexa.toLowerCase();

        Assert.assertEquals(registerBackgroundHexa, "#ef5a00");

        Assert.assertEquals(Color.fromString(registerButton.getCssValue("background-color")).asHex().toLowerCase(), "#ef5a00");


    }

    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        // Chuyển qua tab Đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(2);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        // Verify login button is disabled
        Assert.assertFalse(loginButton.isEnabled());

        // Verify login button = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        // Nhập Email/ Pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("dam@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSeconds(2);

        // Verify login button is enabled
        Assert.assertTrue(loginButton.isEnabled());

        // Verify login button = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");
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
}
