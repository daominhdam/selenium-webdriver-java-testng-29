package javaTester;

public class Topic_02_And_Or {
    public static void main(String[] args) {
        // Member 1
        boolean member01;
        // Member 2
        boolean member02;
        // Result
        boolean result;

        // AND: Chỉ cần 1 trong 2 mà sai => Kết quả sẽ sai (TIÊU CỰC)
        member01 = true;
        member02 = true;
        System.out.println("Result = " + (member01 && member02));

        member01 = true;
        member02 = false;
        System.out.println("Result = " + (member01 && member02));

        member01 = false;
        member02 = true;
        System.out.println("Result = " + (member01 && member02));

        member01 = false;
        member02 = false;
        System.out.println("Result = " + (member01 && member02));

        // OR: Chỉ cần 1 trong 2 mà đúng => Kết quả sẽ đúng (TÍCH CỰC)
        member01 = true;
        member02 = true;
        System.out.println("Result = " + (member01 || member02));

        member01 = true;
        member02 = false;
        System.out.println("Result = " + (member01 || member02));

        member01 = false;
        member02 = true;
        System.out.println("Result = " + (member01 || member02));

        member01 = false;
        member02 = false;
        System.out.println("Result = " + (member01 || member02));
    }

}
