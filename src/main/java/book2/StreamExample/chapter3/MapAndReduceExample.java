package book2.StreamExample.chapter3;

import java.util.ArrayList;
import java.util.List;

public class MapAndReduceExample {

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();

        list.add(new Student("홍길동", 90));
        list.add(new Student("이순신", 52));
        list.add(new Student("신용권", 26));

        Double avg = list.stream()
                .mapToInt(Student::getScore)
                .average()
                .getAsDouble();

        System.out.println("학생의 평균 점수 : " + avg + " 입니다.");
    }
}
