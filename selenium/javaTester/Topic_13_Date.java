package javaTester;

import java.util.Date;

public class Topic_13_Date {
    public static void main(String[] args) {
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
    }

    public static String getDateTimeNow() {
        Date date = new Date();
        return date.toString();
    }
}
