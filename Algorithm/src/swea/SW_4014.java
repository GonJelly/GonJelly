package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 진행중
public class SW_4014 {

    static int n, len;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/swea/sw_4014.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int TC = Integer.parseInt(st.nextToken());

        for( int t = 1; t <= TC; t++ ) {

            st = new StringTokenizer(in.readLine());

            n = Integer.parseInt(st.nextToken()); // 토지의 면적
            len = Integer.parseInt(st.nextToken()); // 경사로의 길이

            int[][] area = new int[n][n];

            for( int r = 0; r < n; r++) {
                st = new StringTokenizer(in.readLine());
                for( int c = 0; c < n; c++) {
                    area[r][c] = Integer.parseInt(st.nextToken());
                }
                System.out.println(Arrays.toString(area[r]));
            }

            dfs(0,area,0);
        }

    }

    private static void dfs(int cnt, int[][] area, int sum) {

        int row = 1;
        boolean[] isCheck = new boolean[n];

        for(int x = 1; x < n; x++ ) {

            if( area[cnt][x] == area[cnt][x - 1] ) {
                row++;
            }
            // 높이가 다르고 경사가 1이면 경사도 설치가능
            else if ( area[cnt][x] != area[cnt][x - 1] && Math.abs(area[cnt][x] - area[cnt][x - 1]) == 1) {
                if( area[cnt][x] > area[cnt][x - 1] ) {

                } else if ( area[cnt][x] < area[cnt][x - 1]) {

                }
            }
            // 높이가 2이상이면 활주로 설치 불가
            else {
                break;
            }

        }

        dfs(cnt + 1, area, sum);

    }

    private static boolean valiable(int[][] area, int x, int y) {



        return true;
    }

}
