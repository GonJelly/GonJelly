package swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_3124_origin {

    static class Node {

        int number;  // 노드 번호
        Node next;   // 노드 번호
        long weight; // 가중치

        public Node(int number, Node next, long weight) {
            this.number = number;
            this.next = next;
            this.weight = weight;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("number=").append(number);
            sb.append(", next=").append(next);
            sb.append(", weight=").append(weight);
            sb.append('}');
            return sb.toString();
        }
    }

    static class Ebge implements Comparable<Ebge>{

        int start;
        int end;
        long weight;

        public Ebge(int start, int end, long weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Ebge o) {
            if( this.weight > o.weight) return 1;
            else if( this.weight < o.weight) return -1;
            else {
                if( this.start > o.start ) return 1;
                else if( this.start < o.start ) return -1;
                else return 0;
            }
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Ebge{");
            sb.append("start=").append(start);
            sb.append(", end=").append(end);
            sb.append(", weight=").append(weight);
            sb.append('}');
            return sb.toString();
        }
    }

    private static void make(int x) {
        node[x] = x;
    }

    private static int find(int x) {
        if( x == node[x] ) return x;
        else return node[x] = find(node[x]);
    }

    private static void union(int a, int b) {
        int f1 = find(a);
        int f2 = find(b);
        if ( f1 == f2 ) return;
        else node[f2] = f1;
    }

    private static int node[];

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(input.nextToken());

        for(int t = 1; t <= T; t++) {

            input = new StringTokenizer(in.readLine());
            int v = Integer.parseInt(input.nextToken());    // 1 <= v <= 100_000    // 정점의 수
            int e = Integer.parseInt(input.nextToken());    // 1 <= E <= 200_000    // 간선의 수
            long ans = 0;
            node = new int[v+1];                           // 최소 신장 트리 생성
            // 집합 생성
            for(int i = 0; i <= v; i++) {
                make(i);
            }

            // 정렬기준 ( 가중치로 오름차순으로 정렬 )
            Queue<Ebge> priorityQue = new PriorityQueue<>();// 가중치 기준으로 정렬하기 위해서 사용 (간선들 저장)

//            ArrayList<Node> list = new ArrayList<>();       // 인접 리스트 생성
//
//            for(int i = 0; i <= v; i++) {
//                list.add(new Node(i,null,0));
//            }

            for(int x = 1; x <= e; x++) {
                input = new StringTokenizer(in.readLine());

                int a = Integer.parseInt(input.nextToken());    // 정점
                int b = Integer.parseInt(input.nextToken());    // 정점
                int c = Integer.parseInt(input.nextToken());    // 가중치

                priorityQue.offer(new Ebge(a,b,c));

                // 양방향성 그래프 생성
//                Node from = list.get(a);
//                Node to = list.get(b);
//
//                list.set(a, new Node(b,from,c));
//                list.set(b, new Node(a,to,c));
            }
            // 그래프 확인
//            list.forEach( node -> {
//                while ( node.next != null ) {
////                    System.out.print(node.number + " => ");
//                    priorityQue.offer(new Ebge(node.number, node.next.number, node.weight));
//                    node = node.next;
//                }
////                System.out.println();
//            });
            // 정렬 확인
//            priorityQue.forEach(System.out::println);

//            System.out.println(Arrays.toString(node));
            // 간선 - 1 만큼 반복
            int count = 0;      // e - 1 간선 확인용
            while( !priorityQue.isEmpty() ) {

                Ebge ebge = priorityQue.poll();
//                System.out.println(ebge);
                int start = ebge.start;
                int end = ebge.end;

                // 같은 그룹 인지 확인
                if ( find(start) == (find(end)) ) continue;

                System.out.println(ebge);

                union(start,end);
                ans += ebge.weight;
                if( ++count == v - 1) break;
            }

//            System.out.println(Arrays.toString(node));
            sb.append("#" + t + " ").append(ans).append("\n");
        }

        System.out.println(sb);

    }
}
