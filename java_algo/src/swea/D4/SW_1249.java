package swea.D4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// GOAL : 복구 작업 시간이 최소인 시간
// 제한 시간 : 20초
// 메모리 제한 : 256MB
public class SW_1249 {

    // 좌,하,우,상
    private static int[][] direct = {
            {0,1},{1,0},{0,-1},{-1,0}
    };
    private static int n;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/swea/supplyRoute3.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken()); // 테스트 케이스
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(in.readLine());
            // 보급로의 크기
            n = Integer.parseInt(st.nextToken());
            // 지도 정보는 그림2에서 보듯이 2차원 배열의 형태이다. ( 최대 크기 100X100 )
            int[][] maps = new int[n][n];
            // 쿨착기가 해당 칸으로 이동하는데 걸리는 시간표기
            int[][] recovery = new int[n][n];

            for(int x = 0; x < n; x++) {

                String row = in.readLine();
                char[] costs = row.toCharArray();

                for(int y = 0; y < n; y++) {
                    maps[x][y] = costs[y] - '0';
                    recovery[x][y] = Integer.MAX_VALUE;
                }
            }

//            print(maps);
//            print(recovery);

            sb.append("#").append(t).append(" ").append(dijktra(maps,recovery)).append("\n");
        }

        System.out.print(sb);
    }

    private static int dijktra(int[][] maps, int[][] recovery) {

        // 방문했는지 확인하는 배열
        boolean[][] visited = new boolean[n][n];
        int min = Integer.MAX_VALUE;
//        PriorityQueue<int[]> queue = new PriorityQueue<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer( new int[] {0,0});
        recovery[0][0] = 0;
        visited[0][0] = true;

        while ( !queue.isEmpty() ) {

            int[] hole = queue.poll();
            int r = hole[0];
            int c = hole[1];
            // 뒤로 탐색하는 일 없도록 방문체크
//            visited[r][c] = true;
//            System.out.println("===============");
//            print(visited);
            if( r == n - 1 && c == n - 1) {
                min = min > recovery[r][c] ? recovery[r][c] : min; // ?
            }

            if( min <= recovery[r][c] ) continue; // ?

            // 4방 탐색
            for( int m = 0; m < 4; m++ ) {
                int nx = r + direct[m][0];
                int ny = c + direct[m][1];
                // 지도의 크기를 벗어나지 않았고!! 방문한적이 없다면
                if( isVail(nx,ny) ) {
//                    recovery[nx][ny] = Math.min(recovery[nx][ny], recovery[r][c] + maps[nx][ny] );
                    if( !visited[nx][ny] || recovery[nx][ny] > recovery[r][c] + maps[nx][ny]) { // ?
                        recovery[nx][ny] = recovery[r][c] + maps[nx][ny];
                        queue.offer(new int[] {nx,ny});
                        visited[nx][ny] = true;
                    }
                    // 해당 장소까지 복구하는데 걸리는 최단 시간 확인
//                    System.out.println(maps[r][c]+"  ===   "+maps[nx][ny]);
//                    System.out.println(r+"<=== row === col ===>"+c);
//                    System.out.println(nx+"<=== row === col ===>"+ny);
                }
//                System.out.println("======== 굴착기 시동 ==========");
//                print(recovery);
            }
//            print(recovery);
        }
//        print(recovery);
        return recovery[n-1][n-1];
    }

    private static boolean isVail(int r, int c) {
        if( r < 0 | r >= n | c < 0 | c >= n ) {
            return false;
        }
        return true;
    }

    private static void print(int[][] obj) {
        System.out.println("========= 축적 ==========");
        for(int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(obj[i]));
        }
    }

    private static void print(boolean[][] obj) {
        for(int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(obj[i]));
        }
    }
}
