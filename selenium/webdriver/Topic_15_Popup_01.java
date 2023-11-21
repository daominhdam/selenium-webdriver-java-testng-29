package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Fixed_Popup_In_DOM_01() {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSeconds(2);

        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");

        // Kiểm tra Login popup đang hiển thị
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(), "Tài khoản không tồn tại!");

        // Close popup
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepInSeconds(2);

        // Kiểm tra Login popup ko hiển thị
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_Popup_In_DOM_02() {
        driver.get("https://skills.kynaenglish.vn/");

        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

        driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
        sleepInSeconds(3);

        // Kiểm tra popup ko còn hiển thị (invisible/ hidden)
        Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());
    }

    @Test
    public void TC_03_Fixed_Popup_Not_In_DOM_01() {
        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        sleepInSeconds(3);

        // Kiểm tra popup hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");

        // Đóng popup
        driver.findElement(By.cssSelector("img.close-img")).click();
        sleepInSeconds(2);

        // Khi cái popup đóng lại thì HTML ko còn trong DOM nữa

        // findElement should not be used to look for non-present elements
        // Assert.assertFalse(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        // findElements(By) and assert zero length response instead

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);
    }

    @Test
    public void TC_04_Fixed_Popup_Not_In_DOM_02() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);

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
