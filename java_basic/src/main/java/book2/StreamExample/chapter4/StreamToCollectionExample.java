package book2.StreamExample.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamToCollectionExample {

    public static void main(String[] args) {

        List<Student> list = new ArrayList<Student>();

        list.add(new Student("홍길동", 90));
        list.add(new Student("신용권", 88));
        list.add(new Student("박준영", 75));

        // 콜렉션의 stream() 메소드를 이용해서 Stream요소를 얻어낼 수 있음
        Stream<Student> stream = list.stream();

        // 이러면 참조주소가 출력된다!!
//        stream.forEach(System.out::println);
        stream.forEach( s -> System.out.println(s.getName()));
    }
}
