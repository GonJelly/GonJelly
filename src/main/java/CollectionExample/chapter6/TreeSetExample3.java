package CollectionExample.chapter6;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample3 {

    public static void main(String[] args) {

        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("apple");
        treeSet.add("forever");
        treeSet.add("description");
        treeSet.add("ever");
        treeSet.add("zoo");
        treeSet.add("base");
        treeSet.add("guess");
        treeSet.add("cherry");

        // 주어진 객체보다 낮은 객체들을 찾기
        NavigableSet<String> lowerSet = treeSet.headSet("c",false);
        for(String word : lowerSet ) {
            System.out.print(word + " ");
        }

        System.out.println();

        // 주어진 객체보다 높은 객체들을 찾기
        NavigableSet<String> higherSet = treeSet.tailSet("l", false);
        for(String word : higherSet) {
            System.out.print(word + " ");
        }

        System.out.println();

        // 주어진 범위사이에 있는 객체 찾기
        NavigableSet<String> subSet = treeSet.subSet("c", false, "n", false);
        for(String word : subSet) {
            System.out.print(word + " ");
        }

    }
}
