package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1149 {

    private static int home, min;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("res/baekjoon/RGB_street1.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        home = Integer.parseInt(st.nextToken());    // 집의 갯수
        min = Integer.MAX_VALUE;
        int[][] costs = new int[home][4];

        for(int r = 0; r < home; r++) {
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= 3; c++) {
                costs[r][c] = Integer.parseInt(st.nextToken());
            }
            System.out.println(Arrays.toString(costs[r]));
        }

        dfs(0,costs,0,0);
        System.out.println(min);
    }

    private static void dfs(int cnt, int[][] costs, int before, int sum) {

        if( cnt == home ) {
            min = Math.min(min,sum);
            return;
        }

        if ( min < sum ) return;

        for( int i = 1; i <= 3; i++) {
            // 전에 칠한 색깔과 같지않다면 책칠하기
            if( i != before ) {
                dfs(cnt + 1,costs, i, sum + costs[cnt][i]);
            }
        }
    }
}
