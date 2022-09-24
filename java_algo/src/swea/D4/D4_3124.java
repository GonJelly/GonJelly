package swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * 재수강하면서 Kruskal을 구현해보았습니다.
 * 처음에는 List에 인접리스트를 작서하고 ...
 * 연결된 가지를 전부 우선순위 큐(priority Queue)에 넣어서 정렬해서 처리하게끔 하였지만
 * 몇차례 수행을 거쳐도 이상한점을 찾지 못해서 다른 학우의 코드를 참조하니
 * List에 넣지않고 바로 우선순위 큐(priority Queue)에 삽입하여 처리하는 것을 보았습니다.
 *
 * [느낀점]
 * Kruskal은 간선리스트를 가지고 처리하는데 이미 연결간선 정보와 가중치를 주는데
 * 리스트에 넣어서 다시 그것을 큐에 넣어서 꺼내면서 처리할 필요가 있을까? 라는 생각을 해서
 * 리스트를 전부 제거 하였습니다.
 *
 * @author 박준영
 *
 */
public class D4_3124{
    // 가지 정보가 들어가는 클래스
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

    }
    // kruskal 알고리즘 필요 연산 start
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
    // kruskal 알고리즘 필요 연산 end

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

            for(int x = 1; x <= e; x++) {
                input = new StringTokenizer(in.readLine());

                int a = Integer.parseInt(input.nextToken());    // 정점
                int b = Integer.parseInt(input.nextToken());    // 정점
                int c = Integer.parseInt(input.nextToken());    // 가중치

                priorityQue.offer(new Ebge(a,b,c));
            }

            // 간선 - 1 만큼 반복
            int count = 0;      // e - 1 간선 확인용
            // 기저조건 : 간선이 없으면 종료
            while( !priorityQue.isEmpty() ) {

                Ebge ebge = priorityQue.poll();

                int start = ebge.start; // 시작 정점
                int end = ebge.end;     // 종료 정점

                // 같은 그룹 인지 확인
                if ( find(start) == (find(end)) ) continue;
                
                union(start,end);   // 부모 정점 합병
                ans += ebge.weight; // 가중치 합산
                // 기저조건 : 간선이 정점 - 1 개가 충족하면 종료
                if( ++count == v - 1) break;
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);

    }
}
