package book1.ExceptionExample;

public class TryCatchFinalLyExample {

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("java.lang.String2");
        } catch (ClassNotFoundException e){
            System.out.println("클래슥 존재하지 않습니다.");
        }
    }

}
