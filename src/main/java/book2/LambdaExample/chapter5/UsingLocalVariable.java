package LambdaExample.chapter5;

public class UsingLocalVariable {

    public void method1 (int arg) {

        int localVar = 50;

        MyFunctionalInterface fi = () -> {

            System.out.println("arg : " + arg);
            System.out.println("localVar : " + localVar);

        };


//        arg = 30; // 람다식에서 오류발생

        fi.robot();

    }

}
