package examplePlace.pack01;

import examplePlace.pack02.Sub01;

public class Main extends Sub01 {

    public static void main(String[] args) {

        Sub01.method01();
        Sub01.variable01 = "String";
        Sub01 sub = new Sub01();
        Sub01 sub_ = new Sub01();

        System.out.println(sub.getVariable01());
        System.out.println(sub_.getVariable01());
        System.out.println("주소보기");
        System.out.println(sub.getVariable01().hashCode());
        System.out.println(sub_.getVariable01().hashCode());

    }
}
