package javaTester;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;

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
    }
}
