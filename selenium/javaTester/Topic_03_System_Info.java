package javaTester;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;

public class Topic_03_System_Info {
    public static void main(String args[]) {

        // Lấy ra đường dẫn tương đối tại thư mục hiện tại
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath + "\\browserDrivers\\geckodriver.exe");

        String osName = System.getProperty("os.name");
        Keys keys;

        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;
    }
}
