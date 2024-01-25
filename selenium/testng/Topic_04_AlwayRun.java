package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_AlwayRun {


    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println("Run Before Class");

        Assert.assertTrue(false);

        // Nó bị fail ở BeforeClass thì các testcase/ after sẽ bị skip
    }

    @Test
    public void TC_01_() {
        System.out.println("Run testcase 01");
    }

    @Test
    public void TC_02_() {
        System.out.println("Run testcase 02");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
        System.out.println("Run After Class");
    }

}
