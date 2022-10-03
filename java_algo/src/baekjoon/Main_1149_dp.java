package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1149_dp {

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("res/baekjoon/RGB_street1.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int home = Integer.parseInt(st.nextToken());    // 집의 갯수
        int[][] costs = new int[home + 1][3];           // 각 집마다 비용
        int[][] dp = new int[home + 1][3];              // 축적 저장

        for(int r = 1; r <= home; r++) {
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < 3; c++) {
                costs[r][c] = Integer.parseInt(st.nextToken());
            }
//            System.out.println(Arrays.toString(costs[r]));
        }

        for( int i = 1; i <= home; i++) {
            // 빨강을 선택했을 경우 직전까지 최적(최소비용)과 합계를 저장
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + costs[i][0];
            // 초록을 선택했을 경우 직전까지 최적(최소비용)과 합계를 저장
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + costs[i][1];
            // 파랑을 선택했을 경우 직전까지 최적(최소비용)과 합계를 저장
            dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1]) + costs[i][2];
        }

//        for(int[] arr : dp) {
//            System.out.println(Arrays.toString(arr));
//        }
        // 마지막까지 선택했을 때 발생하는 최소비용을 선택
        int min = Math.min(dp[home][0],Math.min(dp[home][1],dp[home][2]));

        System.out.println(min);
    }
}
