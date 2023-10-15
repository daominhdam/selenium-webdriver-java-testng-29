package javaTester;

public class Topic_08_Parameter {
    static String fullNameGlobal = "Automation Testing";

    public static void main(String[] args) {
        Topic_08_Parameter topic = new Topic_08_Parameter();

        System.out.println(topic.getFullName());

        System.out.println(fullNameGlobal);

        // Tham số
        topic.setFullName("Manual Testing");

        System.out.println(topic.getFullName());
    }

    public void setFullName(String fullName) { // Tham số
        fullNameGlobal = fullName;
    }

    public String getFullName() {
        return fullNameGlobal;
    }
}
