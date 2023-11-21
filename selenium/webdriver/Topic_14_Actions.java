package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;

    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void beforeClass() {
        // Chromium: Chrome/ Edge/ Cốc Cốc/ Opera/..
        driver = new FirefoxDriver();

        // Nó đang giả lập lại hành vi của Mouse/ Keyboard/ Pen nên khi nó đang chạy mình ko sử dụng các thiết bị này
        actions = new Actions(driver);

        javascriptExecutor = (JavascriptExecutor) driver;

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
        // allNumberTextExpected = List.of(allNumberTextExpectedArray);

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

    @Test
    public void TC_05_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        // Cần scroll tới element rồi mới double Click - chỉ riêng Firefox mới scroll
        if (driver.toString().contains("firefox")) {
            // scrollIntoView(true): kéo mép trên của element lên phía trên cùng của viewport
            // scrollIntoView(false): kéo mép dưới của element xuống phía dưới cùng của viewport
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(false);", doubleClickButton);
            sleepInSeconds(3);
        }

        actions.doubleClick(doubleClickButton).perform();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys");
    }

    @Test
    public void TC_06_RightClick() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        // Chưa click chột phải thì nó đang ko hiển thị (invisible)
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepInSeconds(2);

        // Mới click chuột phải lên - các element sẽ được visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(2);

        // Được cập nhật lại class của element này - kiểm tra sự kiện hover thành công
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

        // Click lên Paste
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(3);

        driver.switchTo().alert().accept();
        sleepInSeconds(3);

        // Kiểm tra Paste ko còn hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
    }

    @Test
    public void TC_07_DragDropHTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        // actions.dragAndDrop(smallCircle, bigCircle).perform();
        actions.clickAndHold(smallCircle).moveToElement(bigCircle).release().perform();
        sleepInSeconds(3);

        Assert.assertEquals(bigCircle.getText(), "You did great!");

        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");
    }

    @Test
    public void TC_08_DragDropHTML5_Css() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String columnA = "div#column-a";
        String columnB = "div#column-b";

        String projectPath = System.getProperty("user.dir");

        String dragAndDropFilePath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";

        String jsContentFile = getContentFile(dragAndDropFilePath);

        jsContentFile = jsContentFile + "$('" + columnA + "').simulateDragDrop({ dropTarget: '" + columnB + "'});";

        // A -> B
        javascriptExecutor.executeScript(jsContentFile);
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        // B -> A
        javascriptExecutor.executeScript(jsContentFile);
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");

    }

    @Test
    public void TC_09_DragDropHTML5_XPath() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
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
