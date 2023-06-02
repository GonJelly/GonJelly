package baekjoon.label2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2146 {

    private static int n, min;
    // 상우하좌
    private static int[][] dir = {
            {-1,0},{0,1},{1,0},{0,-1}
    };

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        // 지도의 크기 1 <= N <= 100
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        int[][] map = new int[n][n];
        boolean[][] visit = new boolean[n][n];
        min = Integer.MAX_VALUE;

        for(int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int x = 0; x < n; x++)  {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
//            System.out.println(Arrays.toString(map[y]));
        }
        // 각 섬마다의 좌표를 저장
        List<ArrayList<int[]>> island = new ArrayList<>();
        // 섬 탐색
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                // 확인한 적없고 육지:1 라면 탐색시작
                if( !visit[y][x] && map[y][x] == 1 ) {
                    island.add(bfs(new int[]{y,x},visit,map));
                }
            }
        }

//        System.out.println(island.size());  // 3

        for(int x = 0, size = island.size() ; x < size; x++) {
            for(int y = x + 1; y < size; y++) {
                min = Math.min(min,getDistance(island.get(x),island.get(y)));
            }
        }

        System.out.println(min - 1);

        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000_000_000 + "s");
        in.close();
    }

    private static ArrayList<int[]> bfs(int[] start, boolean[][] visit, int[][] map) {

        ArrayList<int[]> island = new ArrayList<>();
        Queue<int[]> que = new LinkedList<>();
        que.offer(start);
        visit[start[0]][start[1]] = true;

        while ( !que.isEmpty() ) {

            int[] node = que.poll();
            // 4방 탐색 ( 상우하좌 )
            for( int i = 0; i < 4; i++) {
                int ny = node[0] + dir[i][0];
                int nx = node[1] + dir[i][1];
                // 방문한 적이 없고 // 육지이면 탐색 계속 // 탐색한 적이 없음
                if( isValiable(ny, nx) && map[ny][nx] == 1 && !visit[ny][nx] ) {
                    visit[ny][nx] = true;
                    que.offer(new int[] {ny, nx});
                }

                if( isValiable(ny, nx) && map[ny][nx] == 0 ) {
                    island.add(node);
                }
            }

        }

        return island;
    }

    private static boolean isValiable(int y , int x) {
        if( y >= 0 && y < n && x >= 0 && x < n){
            return true;
        }
        return false;
    }

    private static int getDistance(List<int[]> islandA, List<int[]> islandB) {

        int sizeA = islandA.size();
        int sizeB = islandB.size();

        int minimum = Integer.MAX_VALUE;

        for(int a = 0; a < sizeA; a++) {
            for(int b = 0; b < sizeB; b++) {
                int distance = calc(islandA.get(a),islandB.get(b));
                minimum = Math.min(minimum, distance);
            }
        }

//        System.out.println(minimum);

        return minimum;
    }

    private static int calc(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
