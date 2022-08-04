package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D2_2001 {

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/fly_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());       // 파리장의 크기
            int fly = Integer.parseInt(st.nextToken());     // 파리채의 크기
            int max = 0;                                    // 잡은 파리의 최댓값

            /* 파리장의 크기 선언 및 할당 */
            int[][] board = new int[n + 1][n + 1];
            for(int i = 1; i <= n; i++) {
                st = new StringTokenizer(in.readLine());
                /* 각 행마다 누적으로 파리의 수 축적 */
                for(int j = 1; j <= n; j++) {
                    board[i][j] = board[i][j - 1] + Integer.parseInt(st.nextToken());
                }
            }
            // 파리채의 크기에 맞게 범위지정 (세로 이동)
            for(int start = 1; start <= n - fly + 1; start++) {
                // 파리가 누적된 열 지정
                for (int j = fly; j <= n; j++) {
                    int sum = 0;
                    // 파리채의 크기에 맞게 범위지정 (가로 이동)
                    for (int i = start; i < start + fly; i++) {
                        sum += board[i][j] - board[i][j - fly];
                    }
                    // 최댓값 비교
                    max = Math.max(max,sum);
                }
            }
            // 출력하기
            System.out.printf("#%d %d%n",t,max);
        }

        in.close();
    }
}
