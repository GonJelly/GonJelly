package book2.CollectionExample.chapter8;

import java.util.NavigableSet;
import java.util.TreeSet;

public class ComparableExample {

    /** TreeSet 과 TreeMap은 Comparable을 구현하지 않을 경우 ClassCaseException을 발생시킴
     *
     * 민액. Person 이라는 클래스를 생성하고 TreeSet 또는 TreeMap에 타입파라미터를 주고
     * 방법1 같이 Person클래스를 만들고 TreeSet에 타입 파라미터로 주면 ClassCaseException이 발생한다.
     *
     * 그렇다면 사용자 정의 클래스를 사용하였을 때 예외발생이 발생하지않으려면 어떻게 하면 좋을까?
     * 1. 사용자 정의 클래스에 Comparable 인터페이스를 구현하거나
     * 2. Comparator를 상속한 클래스 즉 정렬자를 TreeSet 또는 TreeMap 생성자 매개변수값으로 전달해주면 된다.
     *
     *
     * */

    public static void main(String[] args) {

        /* 방법1
        TreeSet<Person> treeSet = new TreeSet<Person>();

        // 예외발발
        treeSet.add(new Person("홍길동", 20));
        treeSet.add(new Person("이순신", 43));
        treeSet.add(new Person("세종대", 22));
        treeSet.add(new Person("유희열", 35));
        treeSet.add(new Person("신용권", 40));

        Stream<Person> sortPerson = treeSet.stream().sorted();
        */

        // 사용자 정의 클래스에 Comparable 인터페이스를 구현하여 사용한 예시
        TreeSet<Fruit> treeFruitSet = new TreeSet<>();

        treeFruitSet.add(new Fruit(12, "orange"));
        treeFruitSet.add(new Fruit(5, "cherry"));
        treeFruitSet.add(new Fruit(7, "graph"));
        treeFruitSet.add(new Fruit(16, "apple"));
        treeFruitSet.add(new Fruit(11, "greenApple"));

        NavigableSet<Fruit> descendingSet = treeFruitSet.descendingSet();
        for(Fruit fruit : descendingSet) {
            System.out.println(fruit.getName() + "의 당도는 " + fruit.getBrix() + "입니다.");
        }

        // 사용자 정의 클래스의 Comparable 인터페이스를 구현하지 안혹 따로 Comparator를 상속한 클래스를 매개값으로 주어서 사용 예시
        TreeSet<Car> treeCarSet = new TreeSet<>(new DescendingComparator());

        treeCarSet.add(new Car("Sonata", "Hundai", 12.2));
        treeCarSet.add(new Car("Cona", "Hundai", 10.1));
        treeCarSet.add(new Car("Santafe", "Hundai", 10.5));
        treeCarSet.add(new Car("Tossan", "Hundai", 15.01));
        treeCarSet.add(new Car("abantte", "Hundai", 13));

        NavigableSet<Car> descendingCarSet = treeCarSet.descendingSet();
        for(Car car : descendingCarSet) {
            System.out.print(car.getName() + "-" + car.getRpm() + "\t" );
        }

    }
}
