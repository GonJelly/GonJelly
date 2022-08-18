package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 알파벳
 * @author 박준영
 * 
 */
public class Main_1987 {

    private static int[][] pos = {
            {-1,0},{1,0},{0,1},{0,-1}
    };
    private static int r,c,max;
    private static String[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new String[r+1][c+1];
        visit = new boolean[r+1][c+1];
        for(int i = 1; i <= r; i++) {
            String[] tmp = in.readLine().trim().split("");
            for(int j = 1; j <= c; j++) {
                map[i][j] = tmp[j - 1];
            }
        }
        visit[1][1] = true;
        move(1,1,map[1][1], 1);
        System.out.println(max);
    }

    private static void move(int x, int y,String sb, int cnt) {

        max = Math.max(max,cnt);

        for(int i = 0; i < 4; i++) {

            int nx = x + pos[i][0];
            int ny = y + pos[i][1];

            // 범위를 벗어나지 않고 && 지나온 알파벳이 아니면!!
            if( isCheck( nx, ny) && !(sb.indexOf(map[nx][ny]) > -1) && !visit[nx][ny]) {
                StringBuilder tmp = new StringBuilder(sb).append(map[nx][ny]);
                visit[nx][ny] = true;
                move(nx, ny,tmp.toString() , cnt + 1);
                visit[nx][ny] = false;
            }

        }

    }

    private static boolean isCheck(int x , int y) {
        if( x > 0 && x <= r && y > 0 && y <= c ) {
            return true;
        }
        return false;
    }
}
