package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_4485 {

    private static int[][] direction = {
            {-1,0},{1,0},{0,-1},{0,1}
    };

    private static int n;

    public static void main(String[] args) throws IOException {


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = 0;
        while( true ) {

            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());

            // 동굴의 크기가 0이면 그만
            if ( n == 0 ) break;

            int[][] map = new int[n][n];
            int[][] distance = new int[n][n];
            boolean[][] visit = new boolean[n][n];

            for(int y = 0; y < n; y++) {
                st = new StringTokenizer(in.readLine());
                for(int x = 0; x < n; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    distance[y][x] = Integer.MAX_VALUE;
                }
            }

            distance[0][0] = map[0][0]; // 링크의 초기 시작지점

//            printMap(map);
//            printMap(distance);
//            printMap(visit);

            int r = -1;
            int c = -1;

            while ( r != n -1 || c != n -1 ) {

                int minDistance = Integer.MAX_VALUE;

                for(int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++ ) {
                        if( !visit[y][x] && minDistance > distance[y][x]) {
                            minDistance = distance[y][x];
                            r = y;
                            c = x;
                        }
                    }
                }

                visit[r][c] = true;

                // 4방향 탐색
                for(int i = 0; i < 4; i++) {
                    int ny = r + direction[i][0];
                    int nx = c + direction[i][1];
                    // 동굴 범위를 벗어나면 다음 방향 확인
                    if( valiable(ny,nx)) continue;
                    // 방문한 적이 있으면 다음 방향 확인
                    if( visit[ny][nx] ) continue;

                    distance[ny][nx] = Math.min(distance[ny][nx], distance[r][c] + map[ny][nx] );
                }

//                printMap(distance);
//                printMap(visit);
            }

            sb.append("Problem ").append(++TC).append(": ").append(distance[n - 1][n -1]).append("\n");

        }

        System.out.print(sb);

    }

    private static boolean valiable(int y , int x) {
        // 범위에 있으면 실행을 계속함
        if( x >= 0 && x < n && y >= 0 && y < n ) return false;
        return true;
    }

    private static void printMap(int[][] map) {
        System.out.println("+++++++++++++ 행렬 출력 ++++++++++++++");
        for(int[] tmp : map ) {
            System.out.println(Arrays.toString(tmp));
        }
    }

    private static void printMap(boolean[][] map) {
        System.out.println("+++++++++++++ 행렬 출력 ++++++++++++++");
        for(boolean[] tmp : map ) {
            System.out.println(Arrays.toString(tmp));
        }
    }
}
