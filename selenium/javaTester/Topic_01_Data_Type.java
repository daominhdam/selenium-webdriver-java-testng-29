package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Topic_01_Data_Type {
    // Kiểu dữ liệu trong Java - 2 nhóm

    // I - Kiểu dữ liệu nguyên thủy (Primitive Type)
    // Số nguyên: byte - short - int - long
    // Ko có phần thập phân: nhân viên trong 1 công ty/ học sinh trong 1 lớp/ trường/..
    byte bNumber = 40;
    short sNumber = 3000;
    int iNumber = 15635658;
    long lNumber = 234343400;

    // Số thực: float - double
    // Có phần thập phân: điểm số/ lương/..
    float fNumber = 9.99f;
    double dNumber = 35.800789d;

    // Kí tự: char
    // Đại diện cho 1 kí tự
    char c = '$';
    char d = 'i';

    // Logic: boolean (luận lý)
    // Kết quả bài test: pass/ fail (ko ngoại lệ)
    boolean status = false;

    // II - Kiểu dữ liệu tham chiếu (Reference Type)
    // Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Select select = new Select(firefoxDriver.findElement(By.className("")));
    Topic_01_Data_Type topic01 = new Topic_01_Data_Type();

    By by;

    // Interface
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    // Object
    Object name = "Automation FC";

    // Array (kiểu nguyên thủy/ tham chiếu)
    int[] studentAge = {15, 20, 8};
    String[] studentName = {"Automation", "Testing"};

    // Collection: List/ Set/ Queue
    List<String> studentAddress = new ArrayList<String>();
    List<String> studentCity = new LinkedList<>();
    List<String> studentPhone = new Vector<>();

    // String - Chuỗi kí tự
    String fullName = "Nguyễn Văn Trỗi";

    // Khai báo 1 biến để lưu trữ 1 loại dữ liệu nào đó
    // Access Modifier: Phạm vi truy cập (public/ private/ protected/ default)
    // Kiểu dữ liệu
    // Tên biến
    // Giá trị của biến - gán vào vs phép =
    // Nếu như không gán: dữ liệu mặc định

    public static void main(String[] args) {
        boolean status = true;
        if (status) {
            Assert.assertTrue(true);
        } else {

        }
    }
}
