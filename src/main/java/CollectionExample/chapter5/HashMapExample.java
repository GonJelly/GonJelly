package CollectionExample.chapter5;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {

        // <학생, 점수>
        Map<Student, Integer > map = new HashMap<Student, Integer>();

        map.put(new Student(1, "홍길동"), 97);
        map.put(new Student(1, "홍길동"), 80);

        System.out.println(" 총 객체 수:" + map.size());


    }
}
