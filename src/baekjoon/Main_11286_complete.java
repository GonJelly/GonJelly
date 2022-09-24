package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11286_complete {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if( Math.abs(o1.intValue()) > Math.abs(o2.intValue())) {
                    return 1;
                } else if( Math.abs(o1.intValue()) < Math.abs(o2.intValue()) ) {
                    return -1;
                } else {
                    if( o1.compareTo(o2) > 0) {
                        return 1;
                    } else if ( o1.compareTo(o2) < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });

        for(int i = 0; i < T; i++) {
            int number = Integer.parseInt(in.readLine());
            if( number == 0) {
                if( queue.peek() == null) {
                    System.out.println(0);
                    continue;
                }
                System.out.println(queue.poll());
                continue;
            }
            queue.offer(number);
        }

    }
}
