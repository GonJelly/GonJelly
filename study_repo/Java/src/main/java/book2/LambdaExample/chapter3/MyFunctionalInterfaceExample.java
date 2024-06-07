package book2.LambdaExample.chapter3;

public class MyFunctionalInterfaceExample {
    /** 리턴값이 있는 람다식
    * */
    public static void main(String[] args) {

        MyFunctionalInterface fi = (a,b) -> {
            return a+b;
        };
        int result = fi.method(10, 15);
        System.out.println(result);

        fi = (a,b) -> {return a*b;};
        result = fi.method(10, 2);
        System.out.println(result);

        fi = (a,b) -> a+b;
        result = fi.method(10,15);
        System.out.println(result);

    }
}
