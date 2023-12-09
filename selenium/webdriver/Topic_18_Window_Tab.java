package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Basic_Form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);

        // Switch để qua trang Google
        switchToWindowByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
        sleepInSeconds(3);

        // Switch để quay lại trang Basic Form
        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Facebook – log in or sign up");

        driver.findElement(By.cssSelector("input#email")).sendKeys("dam@gmail.com");
        sleepInSeconds(3);

        // Switch để quay lại trang Basic Form
        switchToWindowByTitle("Selenium WebDriver");
    }

    @Test
    public void TC_02_KynaEnglish() {
        driver.get("https://skills.kynaenglish.vn/");

        String parentID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("div.hotline img[alt='facebook']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");

        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("dam@gmail.com");
        sleepInSeconds(3);

        switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

        driver.findElement(By.cssSelector("div.hotline img[alt='youtube']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Kyna.vn - YouTube");

        Assert.assertEquals(driver.findElement(By.cssSelector("div#inner-header-container yt-formatted-string#text")).getText(), "Kyna.vn");

        closeAllWindowWithoutParent(parentID);
        sleepInSeconds(3);
    }

    @Test
    public void TC_03_TechPanda() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "COMPARE PRODUCTS");

        switchToWindowByTitle("Mobile");

        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        sleepInSeconds(3);
    }

    @Test
    public void TC_04_Selenium_Version_4() {
        // Driver đang ở trang Home
        driver.get("https://skills.kynaenglish.vn/");
        System.out.println("Driver ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        // Window mới - driver nhảy qua Windows mới này nhưng ko có tạo ra driver mới
        driver.switchTo().newWindow(WindowType.WINDOW)
                .get("https://skills.kynaenglish.vn/phan-tich-ky-thuat-trong-chung-khoan");
        System.out.println("Driver ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("a.crs-btn-buy")).click();
        sleepInSeconds(3);

        // Tại Window này - new Tab mới - driver nhảy qua cái Tab mới đó từ Window trước đó
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.facebook.com/kyna.vn");
        System.out.println("Driver ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("dam@gmail.com");
        sleepInSeconds(3);

        switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Java");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("button.search-button")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.content>h4")).getText(), "Lập trình Java trong 4 tuần");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void switchToWindowByID(String expectedID) {
        // Lấy ra hết tất cả tab/ window ID
        Set<String> allIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID trong Set ở trên
        for (String id : allIDs) {
            // Kiểm tra điều kiện trước
            if (!id.equals(expectedID)) {

                // Rồi mới switch sau
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String expectedTitle) {
        // Lấy hết tất cả ID của các window/ tab
        Set<String> allIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua Set ID ở trên
        for (String id : allIDs) {
            // Cho switch vào từng ID trước
            driver.switchTo().window(id);
            sleepInSeconds(2);

            // Lấy ra title của tab/ window hiện tại
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }

    }

    public void closeAllWindowWithoutParent(String parentID) {
        Set<String> allIDs = driver.getWindowHandles();

        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
