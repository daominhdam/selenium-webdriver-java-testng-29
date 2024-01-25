package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_28_Wait_07_Explicit_03 {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");

    String hcmName = "HoChiMinh.jpg";
    String hnName = "HaNoi.jpg";
    String dnName = "DaNang.jpg";

    String hcmFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hcmName;
    String hnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hnName;
    String dnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + dnName;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_AjaxLoading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx?show-source=true");

        By selectedDateBy = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");

        Assert.assertEquals(driver.findElement(selectedDateBy).getText(), "No Selected Dates to display.");

        driver.findElement(By.xpath("//a[text()='12']")).click();

        // Wait cho Loading Icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

        Assert.assertEquals(driver.findElement(selectedDateBy).getText(), "Friday, January 12, 2024");
    }

    @Test
    public void TC_07_() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[text()='Selected Dates:']/following-sibling::div/span")));

        Assert.assertEquals(driver.findElement(By.xpath("//legend[text()='Selected Dates:']/following-sibling::div/span")).getText(), "No Selected Dates to display.");

        driver.findElement(By.xpath("//div[@class='calendarContainer']//a[text()='9']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'absolute')]/div[@class='raDiv']")));

        explicitWait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class='calendarContainer']//a[text()='9']/parent::td"), "class", "rcSelected"));

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='datesContainer']//span")).getText().contains("9"));
    }

    @Test
    public void TC_02_Upload_File() {
        driver.get("https://gofile.io/welcome");

        // Wait + Verify Spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        // Wait + Click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();

        // Wait + Verify Spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(hcmFilePath + "\n" + hnFilePath + "\n" + dnFilePath);

        // Wait + Verify Spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        // Wait Progress Bar biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

        // Wait + Verify button Play có tại từng hình được upload
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hcmName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hnName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + dnName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());

        // Wait + Verify button Download có tại từng hình được upload
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hcmName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hnName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + dnName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
