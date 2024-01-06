package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_Iframe {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Form_Site() {
        // Trang A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(5);

        // Iframe Element
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));

        Assert.assertTrue(formIframe.isDisplayed());

        driver.switchTo().frame(formIframe);

        // Ko tìm thấy element (element nằm trong iframe)
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSeconds(3);

        // Switch ra lại trang A
        driver.switchTo().defaultContent();

        // Thao tác vs 1 element bên ngoài iframe (trang A)
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
    }

    @Test
    public void TC_02_KynaEnglish() {
        driver.get("https://skills.kynaenglish.vn/");

        // Switch vào iframe chứa FanPage
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));

        // Verify followers number
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "169K followers");

        // Switch về trang Default/ Parent chứa iframe
        driver.switchTo().defaultContent();

        // Switch vào iframe WeChat
        driver.switchTo().frame("cs_chat_iframe");

        driver.findElement(By.cssSelector("div.button_bar")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Wick");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987655322");
        new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Đăng kí khóa học JAVA");
        sleepInSeconds(3);

        // Switch về trang Default/ Parent chứa iframe
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Java");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("button.search-button")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.content>h4")).getText(), "Lập trình Java trong 4 tuần");
    }

    @Test
    public void TC_03_Frame_HDFC_Bank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("luis_suarez");
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(5);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());

        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
        sleepInSeconds(5);
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
