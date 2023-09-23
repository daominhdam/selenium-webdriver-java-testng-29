package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    // Các biến được khai báo ở bên ngoài hàm -> phạm vi là Class
    // Biến Global (toàn cục)
    // Có thể dùng cho tất cả các hàm ở trong 1 Class đó
    WebDriver driver;
    String homePageUrl; // Khai báo: Declare
    String fullName = "Automation FC"; // Khai báo + khởi tạo (Initial)

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_() {
        // Các biến được khai báo ở trong 1 hàm/ block code -> phạm vi cục bộ (Local)
        // Dùng trong cái hàm nó được khai báo/ block code được sinh ra
        String homePageUrl = "https://www.facebook.com/";

        // Trong 1 hàm nếu như có 2 biến cùng tên (Global/ Local) thì nó sẽ ưu tiên lấy biến Local dùng
        // 1 biến Local nếu như gọi tới dùng mà chưa được khởi tạo thì sẽ bị lỗi
        // Biến Local chưa khởi tạo mà gọi ra dùng nó sẽ báo lỗi ngay (compile code)
        this.driver.get(homePageUrl);

        // Nếu trong 1 hàm có 2 biến cùng tên (Global/ Local) mà mình muốn lấy biến Global ra để dùng
        // Từ khóa this
        // Biến Global chưa khởi tạo mà gọi ra dùng thì ko báo lỗi ở level (compile code)
        // Level runtime sẽ lỗi
        this.driver.get(this.homePageUrl);
    }

    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }

    @Test
    public void TC_04_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
