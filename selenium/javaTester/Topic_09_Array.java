package javaTester;

public class Topic_09_Array {

    static int[] studentAge = {15, 10, 20, 22};
    static String[] studentName = {"Nguyễn Văn An", "Lê Văn Hòa"};

    public static void main(String[] args) {
        String[] studentAddress = new String[5];

        studentAddress[0] = "Đặng Ngọc Anh";
        studentAddress[1] = "Đào Duy Từ";
        studentAddress[2] = "Nguyễn Trãi";
        studentAddress[3] = "Nguyễn Du";
        studentAddress[4] = "Lê Lợi";

        System.out.println(studentName[0]);
        System.out.println(studentName[1]);

        for (int i = 0; i < studentAddress.length; i++) {
            System.out.println(studentAddress[i]);
        }
    }

}
