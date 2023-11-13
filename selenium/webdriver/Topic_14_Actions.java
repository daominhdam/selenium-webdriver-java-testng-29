package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        // Nó đang giả lập lại hành vi của Mouse/ Keyboard/ Pen nên khi nó đang chạy mình ko sử dụng các thiết bị này
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Menu_Fahasa() {
        driver.get("https://www.fahasa.com/");

        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInSeconds(2);

        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "THIẾT BỊ SỐ - PHỤ KIỆN SỐ");

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());
    }

    @Test
    public void TC_03_ClickAndHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Tổng số chưa chọn
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);

        // Chọn theo Block - Ngang/ Dọc
        // Chọn 1 -> 15 theo hàng/ cột
        actions.clickAndHold(allNumbers.get(0)) // Click lên số 1 và giữ chuột
                .pause(2000) // Dừng lại 2s
                .moveToElement(allNumbers.get(14)) // Di chuột trái đến số 15
                .pause(2000) // Dừng lại 2s
                .release() // Nhả chuột trái ra
                .perform(); // Execute tất cả các action trên

        sleepInSeconds(3);

        String[] allNumberTextExpectedArray = {"1", "2", "3", "5", "6", "7", "9", "10", "11", "13", "14", "15"};

        List.of(allNumberTextExpectedArray);

        // Tổng các số đã chọn
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 12);

        List<String> allNumberTextActual = new ArrayList<String>();

        for (WebElement element : allNumbersSelected) {
            allNumberTextActual.add(element.getText());
            // Assert.assertEquals(element.getCssValue("background-color"), "");
        }

        // Convert từ Array qua ArrayList (List)
        List<String> allNumberTextExpected = Arrays.asList(allNumberTextExpectedArray);

        Assert.assertEquals(allNumberTextExpected, allNumberTextActual);
    }

    @Test
    public void TC_04_ClickAndHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        // Tổng số chưa chọn
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);

        actions.keyDown(cmdCtrl).perform();

        for (int i = 0; i < allNumbers.size(); i++) {
            if (i < 15) {
                actions.click(allNumbers.get(i)).perform();
            }
        }

        actions.keyUp(cmdCtrl).perform();

        sleepInSeconds(3);


        // Tổng các số đã chọn
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 15);
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
