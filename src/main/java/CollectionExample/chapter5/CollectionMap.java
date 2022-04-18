package CollectionExample.chapter5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CollectionMap {

    /** 컬렉션 Map 기본 정의
     *
     * Map의 특징
     * 1. Key 와 value을 쌍으로 객체를 저장한다.
     * 2. Key값으로 사용하는 객체는 중복이 불가능하다.
     * 3. value는 중복이 가능함.
     *
     * 객체추가
     * 객체검색
     * 객체삭제
     *
     * Map인터페이스를 이용해서 구현한 클래스
     * 1. HashMap
     * 2. HashTable
     * 3. LinkedHashMap
     * 4. Properties
     * 5. TreeMap
     *
     * */
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("홍길동", 30);
        map.put("이순신", 10);
        map.put("박혁", 24);

        // map은 key와 value를 쌍으로 객체를 저장하리 깨문에 value를 얻을 때는 key값을 활용한다.
        int result = map.get("홍길동");

        System.out.println("홍길동의 나이는 " + result + "입니다.");

        // 만약 key값을 모른다면 아래와 같이 map의 값을 set객체로 리턴해서 알아낼수 있다.
        Set<String> set = map.keySet();
        for(String convert : set) {
            System.out.println(convert);
        }

        // 이외에도 entrySet() 메소들르 활용하는 방법도 있음
        Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String,Integer>> entryIterator = entrySet.iterator();
        while(entryIterator.hasNext()) {
            Map.Entry<String, Integer> resultentry = entryIterator.next();

            String key = resultentry.getKey();
            int value = resultentry.getValue();

            System.out.println("key 값 : " + key + " value 값 : " + value);
        }
    }
}
