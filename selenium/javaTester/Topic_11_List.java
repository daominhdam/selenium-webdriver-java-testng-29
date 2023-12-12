package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_List {
    @Test
    public void testList() {
        List<String> studentName = new ArrayList<String>();
        studentName.add("Nguyễn Đình Lương");
        studentName.add("Hoàng Văn Nam");
        studentName.add("Nguyễn Ánh Hồng");

        // 3 element trong List
        System.out.println(studentName.size());

        System.out.println(studentName.get(0));

        System.out.println(studentName.get(studentName.size() - 1));

        System.out.println(getSuccessMessage());
    }

    public boolean getSuccessMessage() {
        String text = "Full Name: John Wick";
        if (text.contains("John")) {
            return true;
        }
        return false;
    }
}
