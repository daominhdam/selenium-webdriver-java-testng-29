package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands_01 {
    // Các câu lệnh để thao tác vs Browser
    // driver.
    WebDriver driver;

    // Các câu lệnh thao tác vs Element
    // element.
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        // Muốn dùng được thì phải khởi tạo
        // Nếu ko khởi tạo sẽ gặp lỗi: NullPointerException
        driver = new FirefoxDriver();// **
        /*driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new SafariDriver();*/

        // Selenium ver 3/2/1 (Deprecated)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));// **
    }

    @Test
    public void TC_01_Browser() throws MalformedURLException {
        // Mở ra 1 page Url bất kì
        driver.get("https://www.facebook.com/"); // **

        // Nếu như có 1 tab/ window thì tính năng tương tự quit
        // Nhiều hơn 1 thì nó sẽ đóng cái nó đang active
        driver.close(); // *

        // Đóng browser (ko care bao nhiêu tab/ window)
        driver.quit(); // **

        // 2 hàm này sẽ bị ảnh hưởng timeout của implicitWait
        // findElement/ findElements

        // Nó sẽ đi tìm vs loại By nào và trả về element nếu như được tìm thấy (WebElement)
        // Ko được tìm thấy: Fail tại step này - throw exception: NoSuchElementException
        // Trả về 1 element - nếu như tìm thấy nhiều hơn 1 thì cũng chỉ lấy 1 (thao tác vs cái đầu tiên)
        WebElement emailAddressTextbox = driver.findElement(By.id("email")); // **

        //Nó sẽ đi tìm vs loại By nào và trả về 1 danh sách element nếu như được tìm thấy (List WebElement)
        // Ko được tìm thấy - ko bị fail - trả về 1 List rỗng (0 element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']")); // **

        // Tại sao lại cần phải lấy dữ liệu ra để làm cái gì?
        // Dùng để lấy ra Url của màn hình/ page hiện tại đang đứng
        // Home Page
        driver.getCurrentUrl(); // *

        // Nếu chỉ dùng 1 lần thì ko khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

        // Lấy ra page source HTML/ CSS/ JS của page hiện tại
        // Verify 1 cách tương đối
        driver.getPageSource();
        Assert.assertTrue(driver.getPageSource().contains("The Apple and Google Play logos are trademarks of their respective owners."));

        // Lấy ra title của page hiện tại
        driver.getTitle();

        // Lấy ra ID của cửa sổ/ tab hiện tại
        // Handle Window/ Tab
        driver.getWindowHandle(); // *
        driver.getWindowHandles();// *

        // Cookies - Framework
        driver.manage().getCookies();// *

        // Get ra những log ở Dev Tool - Framework
        driver.manage().logs().get(LogType.DRIVER);// *

        // Apply cho việc tìm element (findElement/ findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // **
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        // Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set trước khi dùng vs thư viện JavascriptExecutor
        // Inject 1 đoạn code JS vào trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Selenium 4 mới có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        // Chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize(); // **
        driver.manage().window().minimize();

        // Test GUI
        // Test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().setSize(new Dimension(2560, 1440));
        driver.manage().window().getSize();

        // Set cho browser ở vị trí nào so vs độ phân giải màn hình (run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().getPosition();

        // Điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        // Thao tác vs history của web page (back/ forward)
        driver.navigate().to("https://www.facebook.com/");
        driver.navigate().to(new URL("https://www.facebook.com/"));

        // Alert/ Window (Tab)/ Frame (iFrame) // *
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        // Lấy ra ID của cửa sổ/ tab hiện tại // *
        // Handle Window/ Tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch/ handle frame (iframe) // *
        // Index/ ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("39493494");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về HTML chứa frame trước đó
        driver.switchTo().defaultContent();

        // Từ frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
