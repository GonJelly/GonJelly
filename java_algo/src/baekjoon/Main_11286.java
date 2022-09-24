package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11286 {

//    static class Heap {
//
//        private int index;
//        private int[] nodes;
//
//        public Heap() {
//            nodes = new int[10];
//        }
//
//        public void add(int data) {
//            if( index >= nodes.length ) {
//                nodes = Arrays.copyOf(nodes,index + 10);
//            }
//
//            if( data == 0) {
//                if( index == 0) {
//                    System.out.print(nodes[1] + "   ");
//                    System.out.println(Arrays.toString(nodes));
//                    return;
//                }
//                System.out.print(nodes[1]+"   ");
//                nodes[1] = nodes[index];
//                nodes[index--] = 0;
//                for(int i = 1; i < index; i*=2) {
//                    if( Math.abs(nodes[i*2]) != 0 && Math.abs(nodes[i]) > Math.abs(nodes[i*2])) {
//                        int temp = nodes[i*2];
//                        nodes[i*2] = nodes[i];
//                        nodes[i] = temp;
//                    } else if( Math.abs(nodes[i]) == Math.abs(nodes[i*2])) {
//                        if( nodes[i] > nodes[i*2] ) {
//                            int temp = nodes[i*2];
//                            nodes[i*2] = nodes[i];
//                            nodes[i] = temp;
//                        }
//                    } else {
//                        break;
//                    }
//                }
//                System.out.println(Arrays.toString(nodes));
//                return;
//            }
//
//            if( nodes[index + 1] == 0) nodes[++index] = data;
//            exchange();
//            System.out.println(Arrays.toString(nodes));
//        }
//
//        private void exchange() {
//            for(int i = index; i > 1; i/=2) {
//                if( Math.abs(nodes[i]) < Math.abs(nodes[i/2])) {
//                    int temp = nodes[i/2];
//                    nodes[i/2] = nodes[i];
//                    nodes[i] = temp;
//                } else if( Math.abs(nodes[i]) == Math.abs(nodes[i/2])) {
//                    if( nodes[i] < nodes[i/2] ) {
//                        int temp = nodes[i/2];
//                        nodes[i/2] = nodes[i];
//                        nodes[i] = temp;
//                    }
//                } else {
//                    break;
//                }
//            }
//        }
//    }

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
