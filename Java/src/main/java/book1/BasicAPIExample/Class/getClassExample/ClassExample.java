package book1.BasicAPIExample.Class.getClassExample;

import book1.BasicAPIExample.Object.CloneExample.Car;

public class ClassExample {

    public static void main(String[] args) {

        Car car = new Car("sonata");

        // getClass() 를 이용해서 객체의 정보 얻어오기
        Class carObject = car.getClass();
        System.out.println(carObject.getName());
        System.out.println(carObject.getSimpleName());
        System.out.println(carObject.getPackage().getName());

        System.out.println();
        // forName을 이용해서 객체의 정보 얻어오기
        // forName은 매개값으로 받는 문자열에 이름을 가진 클래스가 없으면
        // ClassNotFoundExample를 발생시킨다.
        try {
           Class carObject2 = Class.forName("book1.BasicAPIExample.Object.CloneExample.Car");
           System.out.println(carObject2.getName());
           System.out.println(carObject2.getSimpleName());
           System.out.println(carObject2.getPackage().getName());

       } catch (ClassNotFoundException e){
           e.printStackTrace();
       }

    }

}
