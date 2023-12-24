package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);


        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("dam@gmail.com");
        sleepInSeconds(3);

        // Điều kiện 1 - Element có xuất hiện ở trên UI và có trong cây HTML
        // Tại đúng thời điểm này/ step này thì Confirm Email textbox đang visible/ displayed
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra 1 element đang hiển thị
        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }

    @Test
    public void TC_02_Invisible_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        // Điều kiện 2 - Element ko xuất hiện trên UI và vẫn có trong cây HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);

        // Tại đúng thời điểm này/ step này thì Confirm Email textbox đang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra 1 element không hiển thị
        // Chạy nhanh -> Kết quả step này Passed
        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }

    @Test
    public void TC_02_Invisible_Not_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        // Click vào icon Close để đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(3);

        // Điều kiện 3 - Element ko xuất hiện trên UI và cũng ko có trong cây HTML
        // Tại đúng thời điểm này/ step này thì Confirm Email textbox đang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));
    }

    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("dam@gmail.com");
        sleepInSeconds(3);

        // Điều kiện 1 - Element có xuất hiện ở trên UI và có trong cây HTML
        // Tại đúng thời điểm này/ step này thì Confirm Email textbox presence (có trong HTML)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);

        // Điều kiện 2 - Element ko xuất hiện trên UI và vẫn có trong cây HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));
    }

    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        // Tại thời điểm này element có xuất hiện và mình sẽ findElement
        // Attached to the DOM
        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

        // Click vào icon Close để đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(3);

        // Điều kiện 3 - Element ko xuất hiện trên UI và cũng ko có trong cây HTML
        // Wait until an element is no longer attached to the DOM
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
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
