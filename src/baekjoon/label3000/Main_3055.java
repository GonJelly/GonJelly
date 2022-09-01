package baekjoon.label3000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055 {

    private static int R,C,min;
    private static int[] viber, cape;
    private static String[][] map;
    private static Queue<int[]> wave;
    private static boolean[][] visit;
    // 위, 아래, 오른쪽, 왼쪽
    private static int[][] dir = {
            {-1,0},{1,0},{0,1},{0,-1}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        R = Integer.parseInt(st.nextToken());   // 행의 크기
        C = Integer.parseInt(st.nextToken());   // 열의 크기
        min = Integer.MAX_VALUE;                // 최소시간

        wave = new LinkedList<>();              // 홍수의 좌표 넣기
        map = new String[R][C];                 // 티떱숲
        visit = new boolean[R][C];              // 방문여부 판단

        for(int y = 0; y < R; y++) {
            map[y] = in.readLine().split("");
        }

        // 홍수 && 비버 좌표 구하기
        for(int y = 0; y < R; y++) {
            for(int x = 0; x < C; x++) {
                // 물의 좌표 넣기
                if( map[y][x].equals("*") ) wave.offer(new int[] {y,x});
                // 비버의 좌표 넣기
                if( map[y][x].equals("S") ) {
                    visit[y][x] = true;
                    viber = new int[] {y,x};
                }
                if( map[y][x].equals("D") ) cape = new int[] {y, x};
            }
        }

        dfs(0, viber[0], viber[1]);


        in.close();
    }

    private static void dfs(int time, int y, int x) {

        if( time == 7) return;
        if( y == cape[0] && x == cape[1]) {
            System.out.println("탑출 시간->" + time);
            return;
        }
        print(map);
        // 홍수가 펴진다.
        wave();


        // 비버가 움직인다.

        for(int i = 0; i < 4; i++) {

            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            // 범위를 벗어났는지, 돌인지 확인
            if( valiable(ny, nx) ) continue;
            // 물이 있으면 이동불가
            if( map[ny][nx].equals("*") ) continue;
            visit[ny][nx] = true;
            dfs(time + 1, ny, nx);
            visit[ny][nx] = false;

        }

    }

    private static void wave() {
        // 퍼질때 물의 좌표만큼 수행
        int size = wave.size();
        for(int i = 0; i < size; i++ ) {
            // 물의 좌표 확인
            int[] water = wave.poll();
            for (int j = 0; j < 4; j++) {
                int ny = water[0] + dir[j][0];
                int nx = water[1] + dir[j][1];
                // 범위 및 소굴, 돌 체크
                if( valiable(ny, nx) ) continue;
                if( map[ny][nx].equals("D") ) continue;
                // 물 이동
                map[ny][nx] = "*";
                wave.offer(new int[] { ny, nx } );

            }

        }
    }

    private static boolean valiable(int ny , int nx) {
        // 떠텁숲의 범위를 벗어났거나 돌이거나 소굴이면 물은 이동하지않음
        if( ny < 0 || ny >= R || nx < 0 || nx >= C || map[ny][nx].equals("X")) return true;
        return false;
    }

    private static void print(String[][] map) {
        System.out.println("========= 맵 확인 =========");
        for(int y = 0; y < R; y++) {
            System.out.println(Arrays.toString(map[y]));
        }
    }
}
