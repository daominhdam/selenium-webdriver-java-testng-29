package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;

    @Test
    public void verifyTestNG() {
        driver = new FirefoxDriver();

        driver.get("https://www.facebook.com/");

        // Trong Java có nhiều thư viện để verify dữ liệu
        // Testing Framework (Unit/ Intergration/ UI Automation Test)
        // JUnit 4/ TestNG/ JUnit 5/ Hamcrest/ AssertJ/..

        // Kiểu dữ liệu nhận vào là boolean (true/ false)
        // Khi mong muốn điều kiện trả về là đúng thì dùng assertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

        // Mong muốn điều kiện trả về là sai thì dùng assertFalse
        Assert.assertFalse(driver.getPageSource().contains("Create a new account"));

        // Các hàm trả về kiểu dữ liệu là boolean
        // Quy tắc: bắt đầu vs tiền tố là isXXX
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

        // Mong đợi 1 điều kiện nó giống như thực tế (Tuyệt đối)
        // Actual = Expected
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        Assert.assertEquals(driver.findElement(By.id("")).getText(), "Create a new account");


        // Unit Test
        Object name = null;
        Assert.assertNull(name);

        name = "Testing";
        Assert.assertNotNull(name);




    }
}
