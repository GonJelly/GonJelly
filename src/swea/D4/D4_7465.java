package swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_7465 {

    private static boolean[] visit;
    private static int count;       // 그룹 수
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++) {

            StringTokenizer input = new StringTokenizer(in.readLine());

            int n = Integer.parseInt(input.nextToken());    // 사람들 수
            int m = Integer.parseInt(input.nextToken());    // 관계의 수 (간선)

            ArrayList<ArrayList<Integer>> relation = new ArrayList<>();
            visit = new boolean[n + 1];     // 친구 증명 확인
            count = 0;                  // 그룹 수

            for (int x = 0; x <= n; x++) {
                // 사람들이 가지고 있는 관계를 리스트 생성
                relation.add(new ArrayList<Integer>());
            }
            // 친구 리스트 추가
            for (int i = 0; i < m; i++) {
                input = new StringTokenizer(in.readLine());

                int from = Integer.parseInt(input.nextToken());
                int to = Integer.parseInt(input.nextToken());

                // 친구 관계는 양뱡
                relation.get(from).add(to);
                relation.get(to).add(from);
            }
            // 관계의 사이즈 확인
//            relation.forEach(list -> System.out.println("list.size() = " + list));

            // 친구들의 그룹을 확인하기 위해서 : BFS
            for (int x = 1; x <= n; x++) {
                if (!visit[x])
                    bfs(relation, x);
            }

            sb.append("#" + t + " ").append(count + "\n");
        }

        System.out.println(sb);
    }

    private static void bfs(ArrayList<ArrayList<Integer>> relation, int node) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        visit[node] = true;
        // 친구관계 확인
        while ( !queue.isEmpty() ) {

            int dot = queue.poll();
            // 친구의 수
            int size = relation.get(dot).size();
            for(int i = 0; i < size; i++) {
                // 친구관계를 아직 확인하지 않았다면 확인
                if( !visit[relation.get(dot).get(i)] ) {
                    visit[relation.get(dot).get(i)] = true;
                    queue.offer(relation.get(dot).get(i));
                }
            }

        }

        count++;

    }
}
