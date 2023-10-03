package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_07_WebElement_Commands_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element() {
        // Dùng để xóa dữ liệu trong 1 field cho phép nhập (editable)
        // Textbox/ TextArea/ Dropdown (Editable)
        // Thường được sử dụng trước hàm sendKeys
        driver.findElement(By.id("")).clear(); //*

        // Dùng để nhập liệu vào các field bên trên
        driver.findElement(By.id("")).sendKeys(""); //**

        // Dùng để click lên element
        driver.findElement(By.id("")).click(); //**

        driver.findElement(By.xpath("//dkfjsdkfjd"));

        // Tìm từ node cha vào node con
        driver.findElement(By.id("form-validate")).findElement(By.id("firstname"));
        driver.findElement(By.id("form-validate")).findElements(By.cssSelector("input"));

        driver.findElement(By.cssSelector("form#form-validate input#firstname"));

        // Trả về 1 element khớp vs điều kiện
        WebElement fullNameTextbox = driver.findElement(By.id(""));

        // Trả về nhiều element khớp vs điều kiện
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        // Dùng để verify 1 checkbox/ radio/ dropdown (default( đã được chọn hay chưa
        Assert.assertTrue(driver.findElement(By.id("")).isSelected()); //*
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dropdown (default/ custom)
        Select select = new Select(driver.findElement(By.id("")));

        // Dùng để verify 1 element bất kì có hiển thị hay ko
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed()); //**
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        // Dùng để verify 1 element có được thao tác lên hay ko (ko phải read-only)
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled()); //*
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

        // HTML Element
        // <input type="text" id="firstname" name="firstname" value=""
        // title="First Name" maxlength="255" class="input-text required-entry">
        driver.findElement(By.id("")).getAttribute("title"); //*
        driver.findElement(By.id("")).getAttribute("type");
        driver.findElement(By.id("")).getAttribute("id");

        // Tab Accesibility/ Properties trong Elements
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("baseURI");
        driver.findElement(By.id("")).getDomProperty("outerHTML");

        // Tab Styles trong Elements (GUI)
        // Font/ Size/ Color/ Background/..
        driver.findElement(By.id("")).getCssValue("background-color"); //*
        // rgb(46, 138, 184)
        driver.findElement(By.id("")).getCssValue("font-size");
        driver.findElement(By.id("")).getCssValue("text-transform");

        // Vị trí của element so vs độ phân giải màn hình
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        // Chiều cao + rộng
        Dimension addressSize = driver.findElement(By.id("")).getSize();

        // Location + Size
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();

        // Location
        Point namePoint = nameTextboxRect.getPoint();

        // Size
        Dimension nameSize = nameTextboxRect.getDimension();
        nameSize.getHeight();
        nameSize.getWidth();

        // Shadow Element (JavascriptExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        // Từ id/ class/ name/ css/ xpath có thể truy ra ngược lại tagname HTML
        driver.findElement(By.cssSelector("#firstname")).getTagName();  // input
        driver.findElement(By.id("firstname")).getTagName();    // input
        driver.findElement(By.className("form-instructions")).getTagName(); // p

        // Element A -> đầu ra của nó là tagname của element A
        String formListTag = driver.findElement(By.xpath("//*[@class='form-list']")).getTagName();

        // Đầu vào của Element B sẽ nhận tagname của element A là tham số
        driver.findElement(By.xpath("//div[@class='remember-me-popup']/preceding-sibling::" + formListTag));

        driver.findElement(By.cssSelector("address.copyright")).getText(); //**
        // © 2015 Magento Demo Store. All Rights Reserved.

        // Chụp hình bị lỗi và lưu dưới dạng nào
        // BYTE
        // FILE (Lưu thành 1 hình có kích thước ở trong ổ cứng: .png/ .jpg/..)
        // BASE64 (Hình dạng text)
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BASE64); //*
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BYTES);

        // Form (element nào là thẻ form hoặc nằm trong thẻ form)
        // Hành vi giống phím Enter
        // Register/ Login/ Search/..
        driver.findElement(By.id("")).submit();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
