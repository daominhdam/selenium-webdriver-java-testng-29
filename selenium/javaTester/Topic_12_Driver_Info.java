package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_12_Driver_Info {
    WebDriver driver;
    @Test
    public void testDriverInformation() {
        driver = new FirefoxDriver();
        System.out.println(driver.toString());

        // Ở trên OS nào
        // Browser nào
        // Browser class
        // ID của driver là gì
        // FirefoxDriver: firefox on windows (ab67ae7b-6a1b-4a88-8fed-0e2bd2fc571d)
        // FirefoxDriver: firefox on windows (1ce2552f-ff29-4e86-ab67-9631ceaca5ee)

        driver = new ChromeDriver();
        System.out.println(driver.toString());
        // ChromeDriver: chrome on windows (c0e50dc444989be383d41978b9063732)

        if (driver.toString().contains("firefox")) {
            // Scroll
            // Update Code
        }

        driver.quit();
    }
}
