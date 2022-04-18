package CollectionExample.chapter7;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {

    /** TreeMap 은 TreeSet과 차이점
     *
     * 1. Key 와 value를 가진 Map.Entry객체로 저장된다는 점!!
     *
     * 중요한점은 무엇을 기준으로 삼을 것인가?
     *
     * */
    public static void main(String[] args) {

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("홍길동", 97);
        treeMap.put("이순신", 98);
        treeMap.put("세종대", 88);
        treeMap.put("이태원", 99);
        treeMap.put("박혁거", 67);

        Map.Entry<String, Integer> map = treeMap.firstEntry();
        System.out.println(map.getKey() + "의 점수 : " + map.getValue() + "점 입니다.");

    }
}
