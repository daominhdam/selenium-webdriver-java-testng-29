package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_00_Template {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_() {

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        // Tab Accesibility/ Properties trong Elements
        String accessibleName = driver.findElement(By.cssSelector("input#email")).getAccessibleName();
        System.out.println("Accessible name = " + accessibleName);

        driver.get("https://bubble.io/login?mode=signup");

        // https://www.codeinwp.com/blog/wai-aria-roles/
        String ariaRole = driver.findElement(By.cssSelector("iframe[name*='__privateStripeMetricsController']")).getAriaRole();
        System.out.println("Aria Role = " + ariaRole);

//        driver.findElement(By.id("")).getDomAttribute("checked");
//        driver.findElement(By.id("")).getDomProperty("baseURI");
//        driver.findElement(By.id("")).getDomProperty("outerHTML");
//
//        driver.findElement(By.id("")).getShadowRoot();
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
