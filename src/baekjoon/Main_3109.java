package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3109 {

    private static int m,n,max;
    private static char[][] pipe;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(in.readLine());

        m = Integer.parseInt(token.nextToken());
        n = Integer.parseInt(token.nextToken());

        pipe = new char[m][n];
        for(int i = 0; i < m; i++) {
            pipe[i] = in.readLine().toCharArray();
        };

        for(int i = 0; i < m; i++) {
            if ( pipeLine(i,0) ) {
                max++;
            }
        }
        System.out.println(max);
    }

    private static boolean pipeLine(int x, int y) {

        if( y == n - 1) {
            return true;
        }
        // 오른쪽 대각선(위)으로 이동
        if( x > 0 && pipe[x - 1][y + 1] == '.' ) {
            pipe[x - 1][y + 1] = 'P';
            if ( pipeLine( x - 1, y + 1) ) return true;
        }
        // 오른쪽으로 이동
        if( pipe[x][y + 1] == '.' ) {
            pipe[x][y+1] = 'P';
            if ( pipeLine( x, y + 1) ) return true;
        }
        // 오른쪽 대각선(아래)이동
        if( x + 1 < m  && pipe[x + 1][y + 1] == '.' ) {
            pipe[x+ 1][y + 1] = 'P';
            if( pipeLine( x + 1, y + 1) ) return true;
        }

        return false;
    }
}
