package CollectionExample.chapter4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CollectionHashSet {

    /** HashSet의 알고리즘을 알아보자
     * */
    public static void main(String[] args) {

        Set<String> hashSet = new HashSet<String>();

        hashSet.add("JAVA");
        hashSet.add("JDBC");
        hashSet.add("Mybatis");
        hashSet.add("Servlet/JSP");
        hashSet.add("JAVA");
        hashSet.add("JDBC");

        System.out.println("hashSet의 객체의 수 : " + hashSet.size());

        Iterator<String> iterator = hashSet.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        hashSet.remove("JAVA");
        hashSet.remove("JDBC");

        System.out.println("hashSet의 객체의 수 : " + hashSet.size());

        iterator = hashSet.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
