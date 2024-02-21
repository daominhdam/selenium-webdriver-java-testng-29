package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assert {
    WebDriver driver;

    @Test
    public void test01() {
        // Equals = Kiểm tra 2 dữ liệu bằng nhau (Actual - Expected)
        // String/ boolean/ Int/ Float/..
        String fullName = "Automation FC";
        Assert.assertEquals(fullName, "Automation CF", "Actual fullname is not the same!");

        // True - False
        // Điều kiện nhận vào là boolean (isDisplayed/ isEnabled/ isSelected/ isMultiple/ User-Defined/..)

        // Mong đợi cái kết quả trả về là đúng
        // Assert.assertTrue(isElementDisplayed(By.cssSelector("")));
        // Assert.assertTrue(isElementDisplayed(By.cssSelector("")), "Element is not displayed!");

        // Mong đợi cái kết quả trả về là false
       // Assert.assertFalse(isElementDisplayed(By.cssSelector("")));
    }

    private boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

}
