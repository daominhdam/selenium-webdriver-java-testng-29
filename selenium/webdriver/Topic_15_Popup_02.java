package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_02 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Random_Not_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");

        By newsletterPopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        // Nếu hiển thị thì nhảy vào Close nó đi
        // Luôn chạy được vì element luôn có trong HTML/ DOM
        if (driver.findElements(newsletterPopup).size() > 0 && driver.findElements(newsletterPopup).get(0).isDisplayed()) {
            System.out.println("Popup hiển thị");
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a")).click();
            sleepInSeconds(3);
        }

        // Nhập vào field Search dữ liệu
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");

        // Click Search
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());
    }

    @Test
    public void TC_02_Random_In_DOM() {
        driver.get("https://vnk.edu.vn/");

        findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng");
    }

    @Test
    public void TC_03_Random_Not_In_DOM() {
        driver.get("https://dehieu.vn/");

        By marketingPopup = By.cssSelector("div.popup-content");

        if (driver.findElements(marketingPopup).size() > 0 && driver.findElements(marketingPopup).get(0).isDisplayed()) {
            System.out.println("Popup hiển thị");

            int heightBrowser = driver.manage().window().getSize().getHeight();
            System.out.println("Browser height = " + heightBrowser);
            if (heightBrowser < 1920) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
            } else {
                driver.findElement(By.cssSelector("button#close-popup")).click();
            }

            sleepInSeconds(5);
        }

    }

    // Step nào thao tác ở màn hình Home mới gọi hàm này
    public WebElement findElement(By locator) {
        // Nếu popup xuất hiện thì nó sẽ close đi
        if (driver.findElement(By.cssSelector("div.tve-leads-conversion-object")).isDisplayed()) {
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
            sleepInSeconds(3);
        }
        return driver.findElement(locator);
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
