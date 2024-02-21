package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_09_Parameter {

    WebDriver driver;

    @Parameters({"browser", "version"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        driver = getBrowserDriver(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Parameters("environment")
    @Test()
    public void TC_01_Login(@Optional("live") String environmentName) {
        driver.get(getEnvironmentUrl(environmentName) + "/index.php/customer/account/login/");

        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
        driver.findElement(By.xpath("//*[@id='send2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
    }

    private WebDriver getBrowserDriver(String browserName) {
        WebDriver driver;
        if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Browser name is not valid.");
        }
        return driver;
    }

    private String getEnvironmentUrl(String environmentName) {
        String urlValue;
        if (environmentName.equals("dev")) {
            urlValue = "http://dev.techpanda.org";
        } else if (environmentName.equals("testing")) {
            urlValue = "http://testing.techpanda.org";
        } else if (environmentName.equals("staging")) {
            urlValue = "http://staging.techpanda.org";
        } else if (environmentName.equals("live")) {
            urlValue = "http://live.techpanda.org";
        } else {
            throw new RuntimeException("Environment name is not valid.");
        }
        return urlValue;
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
