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
import java.util.List;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;

    // Tường minh: trạng thái cụ thể cho element
    // Visible/ Invisible/ Presence/ Number/ Clickable/..
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));


        // Ngầm định: ko rõ ràng cho 1 trạng thái cụ thể nào của element hết
        // Cho việc tìm element - findElement(s)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Faster");
        sleepInSeconds(3);

        selectItemInDropdown("span#files-button", "ul#files-menu div", "ui.jQuery.js");
        sleepInSeconds(3);

        selectItemInDropdown("span#number-button", "ul#number-menu div", "15");
        sleepInSeconds(3);

        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInDropdown("i.dropdown.icon", "div.item>span.text", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
        sleepInSeconds(3);

        selectItemInDropdown("i.dropdown.icon", "div.item>span.text", "Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
        sleepInSeconds(3);

        selectItemInDropdown("i.dropdown.icon", "div.item>span.text", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
        sleepInSeconds(3);
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
        sleepInSeconds(3);

        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
        sleepInSeconds(3);

        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
        sleepInSeconds(3);
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInEditableDropdown("input.search", "div.item span", "Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
        sleepInSeconds(3);

        selectItemInEditableDropdown("input.search", "div.item span", "Australia");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
        sleepInSeconds(3);

        selectItemInEditableDropdown("input.search", "div.item span", "Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
        sleepInSeconds(3);
    }

    @Test
    public void TC_05_NopCommerce() {
        driver.get("https://demo.nopcommerce.com/register");

        selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "18");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='18']")).isSelected());
        sleepInSeconds(2);

        selectItemInDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "September");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='9']")).isSelected());
        sleepInSeconds(2);

        selectItemInDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "1995");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='1995']")).isSelected());
        sleepInSeconds(2);
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

    // Khi các bạn làm cho 1 dự án khác vs hành vi khác thì cần sửa hàm lại theo đúng hành vi của app đó
    // Cypress/ Playwright/ WebDriverIO/...
    // Tự viết hàm: Python/ JS/ Ruby/..
    // Vòng lặp/ break/..
    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        sleepInSeconds(1);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
        sleepInSeconds(1);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
}