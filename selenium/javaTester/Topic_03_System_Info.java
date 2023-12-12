package javaTester;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;

import java.io.File;

public class Topic_03_System_Info {
    public static void main(String args[]) {

        String osName = System.getProperty("os.name");
        Keys keys;

        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        // Lấy ra đường dẫn tương đối tại thư mục hiện tại
        String projectPath = System.getProperty("user.dir");

        // String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";

        String hcmName = "HoChiMinh.jpg";
        String hnName = "HaNoi.jpg";
        String dnName = "DaNang.jpg";

        String hcmFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hcmName;
        String hnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hnName;
        String dnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + dnName;

        System.out.println(hcmFilePath);
        System.out.println(hnFilePath);
        System.out.println(dnFilePath);
    }
}
