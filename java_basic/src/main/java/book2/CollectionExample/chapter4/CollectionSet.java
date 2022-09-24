package book2.CollectionExample.chapter4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CollectionSet {

    /** Set에 특성을 알아보자
     *
     * 1. 같은 객체는 중복으로 저장이 안된다.!!
     * 2. 집합과 비슷해서 가여오는 객체를 순서로 정리하지 않는다.
     *
     * */
    public static void main(String[] args) {

        Set<String> set = new HashSet<String>();

        set.add("Spring");
        set.add("java");
        set.add("홍길동");
        set.add("JPA");
        set.add("Spring");

        Iterator<String> iterator = set.iterator();

        // set 메소드 Iterator()[반복자]를 이용해서 객체 출력하기
        if(iterator.hasNext()) {
            for(int i = 0; i < set.size(); i++) {
                String result = iterator.next();
                System.out.println(result);
            }
        }

        System.out.println("=======================");

        // 확장된 for문을 활용하여 iterator를 사용하지 않고 출력하기
        for(String result : set) {
            System.out.println(result);
        }

        System.out.println("=======================");

        // 스트림을 활용하여 출력하기
        set.stream().forEach(oc -> System.out.println(oc));

    }

}
