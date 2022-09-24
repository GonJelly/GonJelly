package book2.StreamExample.chapter1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

// 학생 이름과 성적을 출력하시오.

public class LamdaExdpressionsExample {


    public static void main(String[] args) {

        List<Student> list = new ArrayList<Student>();

        list.add(new Student("홍길동", 90 ));
        list.add(new Student("이순신", 78));
        list.add(new Student("이소룡", 68));
        list.add(new Student("박영걸", 88));

        // 반복자 Iterator를 사용하여 출력하는 방법
        Iterator<Student> iterator = list.iterator();
        while(iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println("학생의 이름 :" + student.getName() + " 점수 : " + student.getScore());
        }

        System.out.println();

        // Stream을 사용하여 출력하는 방법
        Stream<Student> stream = list.stream();
        stream.forEach( s -> System.out.println("학생의 이름 : " + s.getName() + " 점수 : " + s.getScore()));
    }
}
