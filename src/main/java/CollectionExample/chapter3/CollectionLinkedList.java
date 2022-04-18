package CollectionExample.chapter3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectionLinkedList {

    /** ArrayList와 LinkedList의 속도차이 분석
     *
     * */
    public static void main(String[] args) {

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new LinkedList<String>();

        long startTime;
        long endTime;


        // ArrayList 삽입하는데 걸린 시간
        startTime = System.nanoTime();
        for(int i = 0; i < 10000; i++){
            list1.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();

        System.out.println("걸린 시간 : " + (endTime - startTime) + "ns");

        // LinkedList 삽입하는데 걸린 시간
        startTime = System.nanoTime();
        for(int i = 0; i < 10000; i++) {
            list2.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();

        System.out.println("걸린 시간 : " + (endTime - startTime) + "ns");

    }

}
