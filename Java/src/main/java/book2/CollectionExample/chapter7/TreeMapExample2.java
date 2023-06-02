package book2.CollectionExample.chapter7;

import java.util.*;

public class TreeMapExample2 {

    /** TreeMap도 TreeSet과 동일하게 정렬하는 메소드를 지원한다.
     *
     * 1. descendingKeySet()
     * 해당 메소드는 내림차순으로 정렬된 NavigableSet을 리턴한다.
     *
     * 2. descendingMap()
     * 해당 메소드는 내림차순으로 정렬된 Map.Entry의 NavigableMap을 리턴한다.
     *
     * descendingMap() 메소드로 리턴된 NavigableMap은 정렬 순서를 바꿀 수 있는 메소드를 지원한다.
     * 즉, 내림차순을 오름차순으로 바꿀 수 있는 메소드를 제공한다.
     *
     * */
    public static void main(String[] args) {

        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();

        treeMap.put(85, "홍길동");
        treeMap.put(75, "이순신");
        treeMap.put(95, "박길순");
        treeMap.put(97, "신용권");
        treeMap.put(81, "백소리");
        treeMap.put(71, "유희왕");
        treeMap.put(60, "나랑드");
        treeMap.put(99, "요르문");

        NavigableSet<Integer> descendingset = treeMap.descendingKeySet();
        for(int score : descendingset) {
            System.out.print(score + " ");
        }

        NavigableMap<Integer, String> navigableMap = treeMap.descendingMap();
        Map.Entry<Integer,String> entryMap = navigableMap.firstEntry();
        System.out.println("점수가 가장 낮은 사람 : " + entryMap.getValue() + " 저수 : " + entryMap.getKey());

        entryMap = navigableMap.lastEntry();
        System.out.println("점수가 가장 높은 사람 : " + entryMap.getValue() + " 저수 : " + entryMap.getKey());

        // 내림차순으로 정렬
        for(Map.Entry<Integer, String> scores : navigableMap.entrySet()) {
            System.out.print(scores.getValue() + "-" + scores.getKey() + " ");
        }

        System.out.println();

        Set<Map.Entry<Integer, String>> entrySet = navigableMap.entrySet();
        for(Map.Entry<Integer, String> scores : entrySet) {
            System.out.print(scores.getValue() + "-" + scores.getKey() + " ");
        }

        System.out.println();

        // 오름차순으로 정렬
        NavigableMap<Integer, String> ascendingMap = navigableMap.descendingMap();
        for(Map.Entry<Integer, String> scores : ascendingMap.entrySet()) {
            System.out.print(scores.getValue() + "-" + scores.getKey() + " ");
        }

    }
}
