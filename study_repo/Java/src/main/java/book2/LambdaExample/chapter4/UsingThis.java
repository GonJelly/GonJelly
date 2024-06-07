package book2.LambdaExample.chapter4;

public class UsingThis {

    int outterField = 10;

    class Inner {

        int innerField = 20;

        void method() {

            MyFunctionalInterface fi = () -> {

                System.out.println("this 참조객체 필드값: " + outterField);
                System.out.println("this 참조객체 필드값: " + UsingThis.this.outterField);

                System.out.println("this 참조객체 필드값: " + innerField);
                System.out.println("this 참조객체 필드값: " + this.innerField);

            };
            fi.method();
        }
    }
}
