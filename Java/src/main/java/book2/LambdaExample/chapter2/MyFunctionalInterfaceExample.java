package book2.LambdaExample.chapter2;

public class MyFunctionalInterfaceExample {
    /** 매개변수가 있는 람다식
     * */
    public static void main(String[] args) {

        MyFunctionalInterface fi = (a) -> {
            int result = a + 10;
            System.out.println(result);
        };
        fi.method(10);

        fi = (a) -> {
            int result = a*10;
            System.out.println(result);
        };
        fi.method(3);

        fi = (a) -> System.out.println(a+15);
        fi.method(5);
    }

}
