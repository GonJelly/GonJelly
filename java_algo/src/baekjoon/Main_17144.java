package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17144 {
    // 4방향 확산하기 위한 변수
    private static int[][] direction = {
            {1,0},{-1,0},{0,1},{0,-1}
    };
    private static int R,C,T;
    private static List<int[]> cleaner;
    private static List<int[]> dust;
    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열
        T = Integer.parseInt(st.nextToken());   // 시간

        int dust_Cnt = 0;

        cleaner = new ArrayList<>();
        dust = new ArrayList<>();

        int[][] room = new int[R][C];
        // 미세먼지 할당
        for(int r = 0; r < R; r++ ) {
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < C; c++) {
                room[r][c] = Integer.parseInt(st.nextToken());
                // 공기청정기 위치
                if( room[r][c]  == -1 ) cleaner.add(new int[] {r,c});
                // 미세먼지의 위치
                else if( room[r][c]  != 0) dust.add(new int[] {r,c});
            }
//            System.out.println(Arrays.toString(room[r]));
        }
        for( int i = 0 ; i < T; i++) {
            room = extension(room);
        }
//        System.out.println("미세먼지의 좌표 start");
//        dust.forEach(arr -> System.out.println(Arrays.toString(arr)));
//        System.out.println("미세먼지의 좌표 end");

//        for (int[] tmp: room ) {
//            System.out.println(Arrays.toString(tmp));
//        }
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                dust_Cnt += room[r][c];
            }
        }

        System.out.println(dust_Cnt);
        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000_000_000 + "s");
        in.close();
    }

    private static int[][] extension(int[][] room) {

        int[][] tmp = new int[R][C];

        // 미세먼지가 있는 좌표에서 확산이 이루어진다.
        for(int x = 0; x < dust.size(); x++) {
            // 확산량
            int total = 0;

            // 4방향 탐색
            for(int y = 0; y < 4; y++) {
                int nx = dust.get(x)[0] + direction[y][0];
                int ny = dust.get(x)[1] + direction[y][1];
                // 확산가능한 구간 and 공기청정기가 없어야함
                if( valiable(nx,ny) && room[nx][ny] != -1 ) {
                    tmp[nx][ny] += room[dust.get(x)[0]][dust.get(x)[1]] / 5;   // 확산되는 양
                    total += room[dust.get(x)[0]][dust.get(x)[1]] / 5;    // 확산된 양을 합산
                }
            }
            // 확산이 끝났으면 확산된 양만큼 줄인다.
            tmp[dust.get(x)[0]][dust.get(x)[1]] += room[dust.get(x)[0]][dust.get(x)[1]] - total;
        }
//        System.out.println("======== 공기청정기 작동 전 ==========");
//        for ( int[] arr : tmp) {
//            System.out.println(Arrays.toString(arr));
//        }

        // 공기청정기 작동
        tmp = excute(tmp);

//        System.out.println("======== 공기청정기 작동 후 ==========");
//        for ( int[] arr : tmp) {
//            System.out.println(Arrays.toString(arr));
//        }

        dust.clear();   // 미세먼지 좌표 클리어
        // 좌표 최신화
        for(int r = 0 ; r < R; r++) {
            for(int c = 0; c < C; c++ ) {
                if( tmp[r][c] != 0) {
                    dust.add(new int[] { r, c});
                }
            }
        }
        return tmp;
    }

    private static int[][] excute(int[][] room) {

        int r = cleaner.get(0)[0];
        int c = cleaner.get(0)[1];

//        System.out.println(r + ":::" + c);
        // 공기 청정기 작동 ( 위 )
        for(int x = r; x > 0 ;x--) room[x][c] = room[x - 1][c];
        room[r][c] = 0;  // 공기청정기로 흡입된 미세먼지 제거
        for(int y = c; y < C - 1 ;y++) room[0][y] = room[0][y + 1];
        for(int x = 0; x < r ;x++) room[x][C - 1] = room[x + 1][C - 1];
        for(int y = C - 1; y > c ;y--) room[r][y] = room[r][y - 1];

        r = cleaner.get(1)[0];
        c = cleaner.get(1)[1];

//        System.out.println(r + ":::" + c);
        // 공기 청정기 작동 ( 아래 )
        for(int x = r; x < R - 1 ;x++) room[x][c] = room[x + 1][c];
        room[r][c] = 0;  // 공기청정기로 흡입된 미세먼지 제거
        for(int y = c; y < C - 1 ;y++) room[R - 1][y] = room[R - 1][y + 1];
        for(int x = R - 1; x > r ;x--) room[x][C - 1] = room[x - 1][C - 1];
        for(int y = C - 1; y > c ;y--) room[r][y] = room[r][y - 1];

        return room;
    }
    // 범위 체크
    private static boolean valiable(int x, int y) {

        if( x >= 0 && x < R && y >= 0 && y < C) {
            return true;
        }
        return false;

    }
}
