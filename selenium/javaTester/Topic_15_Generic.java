package javaTester;

import java.util.ArrayList;
import java.util.List;

public class Topic_15_Generic {

    public static void main(String[] args) {
        // List chi chua kieu du lieu String
        // E T V K L: the type of elements in this list
        List<String> students = new ArrayList<String>();
        students.add("John");
        students.add("Mary");
        students.add("Peter");
        students.add("Nam");

        // non-Generic
        List addresses = new ArrayList<>();
        addresses.add("123 Main St."); // String
        addresses.add(15); // Integer
        addresses.add(true); // Boolean
        addresses.add(15.56); // Float

    }

}
