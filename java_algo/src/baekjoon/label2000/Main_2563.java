package baekjoon.label2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2563 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        /* 색종이의 좌표 start */
        int[][] colors = new int[n+1][n+1];
        int[][] paper = new int[101][101];
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            colors[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        /* 색종이의 좌표 end */

        for(int i = 1; i <= n; i++) {
            for(int x = colors[i][0]; x < colors[i][0] + 10; x++) {
                for(int y = colors[i][1]; y < colors[i][1] + 10; y++) {
                    if( paper[x][y] == 0) {
                        paper[x][y] = 1;
                    }
                }
            }
        }

        for(int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if( paper[i][j] == 1 ) {
                    sum += 1;
                }
            }
        }

        System.out.println(sum);
    }
}
