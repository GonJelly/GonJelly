package baekjoon.label3000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055_bfs {

    private static int R,C,min;
    private static int[] viber;

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

        Queue<int[]> wave = new LinkedList<>();              // 홍수의 좌표 넣기
        String[][] map = new String[R][C];                 // 티떱숲
        boolean[][] visit = new boolean[R][C];              // 방문여부 판단

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
            }
        }

        bfs(0,viber[0], viber[1], map , visit, wave);

        if( min != Integer.MAX_VALUE ) {
            System.out.println(min);
        } else {
            System.out.println("KAKTUS");
        }
        in.close();
    }

    private static void bfs(int time, int y, int x , String[][] map, boolean[][] visit , Queue<int[]> wave) {

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {y,x,0});
        wave(map, wave);
//        print(map);

        // 비버가 움직인다.
        while ( !que.isEmpty() ) {

            int[] viver = que.poll();
            visit[viver[0]][viver[1]] = true;
//            System.out.println(Arrays.toString(viver));

            if( map[viver[0]][viver[1]].equals("*") ) continue;
            if( map[viver[0]][viver[1]].equals("D")) {
                min = viver[2];
                return;
            }
            // 시간이 갱신되면 홍수가 펴진다.
            if( time < viver[2] ) {
                wave(map, wave);
//                print(map);
                time = viver[2];
            }

            for(int i = 0; i < 4; i++) {

                int ny = viver[0] + dir[i][0];
                int nx = viver[1] + dir[i][1];
                // 범위를 벗어났는지, 돌인지 확인
                if( valiable(ny, nx) ) continue;
                // 물이 있으면 이동불가
                if( map[ny][nx].equals("*") || map[ny][nx].equals("X")) continue;
                if( !visit[ny][nx] ) {
                    visit[ny][nx] = true;
                    que.offer(new int[] {ny,nx, viver[2] + 1});
                }
            }

        }

    }

    private static void wave(String[][] map , Queue<int[]> wave) {
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
                if( map[ny][nx].equals("D") || map[ny][nx].equals("X") ) continue;
                // 물 이동
                map[ny][nx] = "*";
                wave.offer(new int[] { ny, nx } );

            }

        }
    }

    private static boolean valiable(int ny , int nx) {
        // 떠텁숲의 범위를 벗어났거나 돌이거나 소굴이면 물은 이동하지않음
        if( ny < 0 || ny >= R || nx < 0 || nx >= C ) return true;
        return false;
    }

    private static void print(String[][] map) {
        System.out.println("========= 맵 확인 =========");
        for(int y = 0; y < R; y++) {
            System.out.println(Arrays.toString(map[y]));
        }
    }
}
