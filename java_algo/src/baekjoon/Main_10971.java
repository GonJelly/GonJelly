package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10971 {

    /*
    * 외판원 순회 2
    * 1 ~ N번호까지 매겨져 있는 도시들이 있고
    * 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로
    * 한번 갔던 도시는 다시 갈 수 없다.
    *
    * 최소 비용으로 순회 여행 경로를 구하는 프로그램
    *
    * */

    private static int n, min;
    private static int[] minEbge;
    private static int[][] arr;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());    // 도시의 수
        visit = new boolean[n];
        min = Integer.MAX_VALUE;

        // 인접행렬
        arr = new int[n][n];
        for(int x = 0; x < n; x++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int y = 0; y < n; y++) {
                arr[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        for(int node = 0; node < n; node++) {
            dfs(0,node,node,0);
        }

        System.out.println(min);

    }

    private static void dfs(int cnt,int start, int node, int sum) {

        if ( cnt == n && start == node) {
            min = Math.min(min, sum);
            return;
        }

        for(int i = 0; i < n; i++) {
            if( !visit[i] && arr[node][i] > 0 ) {
                visit[i] = true;
                if( sum + arr[node][i] < min ) {
                    dfs(cnt + 1, start, i, sum + arr[node][i]);
                }
                visit[i] = false;
            }
        }

    }
}
