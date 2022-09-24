package book2.CollectionExample.chapter7;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapExample3 {

    /** TreeMap의 범위검색
     *
     * TreeMap도 TreeSet과 비숫하게 범위검색 메소드를 지원하고 있다.
     *
     * 1. headMap( key값 , key값 포함여부 )
     * 해당 메소드는 주어진 키값보다 낮은 키객체(Map.Entry)들을 NavigableMap으로 리턴
     *
     * 2. tailMap( key값 , key값 포함여부 )
     * 해당 메소드는 주어진 키값보다 높은 키객체(Map.Entry)들을 NavigableMap으로 리턴
     *
     * 3. subMap( 시작 key값, 시작 key값 포함여부 , 종료 key값 , 종료 key값 포함여부 )
     * 해당 메소드는 시작 key값보다 높고, 종료키값 보다 낮은 키객체(Map.Entry)들을 리턴해준다.
     * 즉, 시작과 종료 사이에 있는 키값을 리턴
     * ( 시작 key < 찾는 키 < 종료 key ) or ( 시작 key <= 찾는 키 <= 종료 key )
     *
     * String객체는 유니코드를 기반으로 정렬한다.!!
     *
     * */
    public static void main(String[] args) {

        TreeMap<String , Integer> treeMap = new TreeMap<>();

        treeMap.put("apple", 60);
        treeMap.put("forever", 85);
        treeMap.put("ever", 90);
        treeMap.put("description", 77);
        treeMap.put("zoo", 10);
        treeMap.put("base", 20);
        treeMap.put("guess", 70);
        treeMap.put("cherry", 96);
        treeMap.put("korea", 55);

        NavigableMap<String, Integer> navigableMap = treeMap.headMap("c", false);
        for(Map.Entry<String, Integer> rangMap : navigableMap.entrySet()) {
            System.out.print(rangMap.getKey() + "-" + rangMap.getValue() + "\t");
        }

        System.out.println();

        navigableMap = treeMap.tailMap("f", false);
        for(Map.Entry<String, Integer> rangMap : navigableMap.entrySet()) {
            System.out.print(rangMap.getKey() + "-" + rangMap.getValue() + "\t");
        }

        System.out.println();

        navigableMap = treeMap.subMap("d", false, "g", false);
        for(Map.Entry<String,Integer> rangMap : navigableMap.entrySet()) {
            System.out.print(rangMap.getKey() + "-" + rangMap.getValue() + "\t");
        }
    }
}
