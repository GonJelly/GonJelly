package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW_Problem2 {

    static class Node{
        int number;
        int y;
        int x;
        int weight;

        public Node(int number, int y, int x, int weight) {
            this.number = number;
            this.y = y;
            this.x = x;
            this.weight = weight;
        }

    }

    private static int min;

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for (int t = 1; t <= T; t++) {

            int n = Integer.parseInt(in.readLine());
            min = Integer.MAX_VALUE;
            int[][] map = new int[n][n];

            List<Node> node = new ArrayList<>();

            for (int y = 0; y < n; y++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int x = 0; x < n; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    if (map[y][x] < 0) node.add(new Node(map[y][x], y, x, 1));
                    if (map[y][x] > 0) node.add(new Node(map[y][x], y, x,0));
                }
            }

            dfs(0,0,0,new int[] {0,0},node);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.print(sb);
        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000_000_000 + "s");
        in.close();
    }

    private static void dfs(int cnt, int flag , int sum, int[] hunter, List<Node> nodes) {

        if( cnt == nodes.size() ) {
            min = Math.min(min,sum);
            return;
        }

        if( sum > min) return;


        for(int i = 0; i < nodes.size(); i++) {
            if( ( flag & 1<<i) != 0 || nodes.get(i).weight != 0) continue;
            int idx = -1;
            int distance = getDistance(hunter,nodes.get(i) );
            // 해당 몬스터를 잡았을 경우 고객에게 갈 수 있도록 수정
            for(int x = 0; x < nodes.size(); x++) {
                // 번호가 음수라면 고객 || 고객의 몬스터를 잡았으면 해당 고객 방문 허용
                if ( nodes.get(x).number < 0 && nodes.get(x).number == -nodes.get(i).number) {
//                    System.out.println("몬스터의 번호 확인 :" + nodes.get(i).number);
                    nodes.get(x).weight = 0;
                    idx = x;
                }
            }
            int[] move = new int[] {nodes.get(i).y,nodes.get(i).x};
            dfs(cnt + 1, flag | 1<<i, sum + distance, move, nodes);
            // 방문 차단
            if ( idx >= 0) nodes.get(idx).weight = 1;
        }
    }

    private static int getDistance ( int[] hunter, Node node) {
        return Math.abs(hunter[0] - node.y) + Math.abs(hunter[1] - node.x);
    }
}
