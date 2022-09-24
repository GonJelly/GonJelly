package book2.CollectionExample.chapter6;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample2 {

    /** TreeSet 정렬 메소드
     *
     * descendingIterator()
     * 내림차순으로 정렬된 Iterator를 리턴
     *
     * descendingNavigableSet()
     * 내림차순으로 정렬된 NavigableSet을 리턴
     *
     * NavigableSet은 TreeSet과 마찬가지로 first(), Last(), lower() 등등 메소드를 제공
     * 그리고 정렬순서를 바꾸는 descendingSet() 메소드를 제공!!
     *
     * */

    public static void main(String[] args) {

        TreeSet<Integer> treeSet = new TreeSet<Integer>();

        treeSet.add(new Integer(95));
        treeSet.add(new Integer(90));
        treeSet.add(new Integer(65));
        treeSet.add(new Integer(99));
        treeSet.add(new Integer(81));

        // 내림차순으로 정렬 [descendingSet()]
        NavigableSet<Integer> descendingSet = treeSet.descendingSet();
        for(int score : descendingSet) {
            System.out.print(score + " ");
        }

        System.out.println();

        // 내림차순으로 정렬 [descendingIterator()]
        Iterator<Integer> descendingIterator = treeSet.descendingIterator();
        while(descendingIterator.hasNext()) {
            int score = descendingIterator.next();
            System.out.print(score + " ");
        }

        System.out.println();

        // 오름차순으로 정렬
        NavigableSet<Integer> ascendingSet = descendingSet.descendingSet();
        for (int score : ascendingSet) {
            System.out.print(score + " ");
        }

    }
}
