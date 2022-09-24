package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10026_bfs {

    private static final String RED = "R";
    private static final String GREEN = "G";
    private static final String BLUE = "B";

    private static void bfs(int r, int c,String[][] map,String color) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r,c});
        isvisit[r][c] = true;
        // 탐색 start
        while ( !queue.isEmpty() ) {

            int[] node = queue.poll();
            // 4방향 탐색 start
            for(int i = 0; i < 4; i++) {
                int nr = node[0] + direction[i][0];
                int nc = node[1] + direction[i][1];
                // 범위를 벗어나면 다음탐색
                if( isCheck(nr,nc) ) continue;
                // 탐색한 적이 없고 탐색하려는 색과 동일하면 실행
                if( !isvisit[nr][nc] && color.contains(map[nr][nc]) ) {
                    isvisit[nr][nc] = true;
                    queue.offer(new int[] {nr,nc});
                } // 조건과 일치하면 추가 end
            } // 방향 탐색 end
        } // 탐색 end

        count++;
    }

    private static boolean isCheck(int r, int c) {
        if( r >= 0 && r < n && c >= 0 && c < n) {
            return false;
        } else {
            return true;
        }
    }

    private static int n, count;
    private static boolean[][] isvisit;
    private static int[][] direction = {
            {-1,0},{0,1},{1,0},{0,-1}
    };
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(in.readLine());
        StringBuilder out = new StringBuilder();
        long start = System.nanoTime();
        // 그리드의 크기 입력
        n = Integer.parseInt(input.nextToken());
        // 그룹 초기화

        // 그리드 선언
        String[][] grid = new String[n][n];

        // 그리드 할당
        for(int x = 0; x < n; x++ ) {
            grid[x] = in.readLine().split("");
            System.out.println(Arrays.toString(grid[x]));
        }

        // 방문여부 체크 초기화
        isvisit = new boolean[n][n];
        count = 0;

        // 적록색약가 아닌 경우
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                // 탐색하려는 색이 파란색
                if( grid[r][c].equals(BLUE) ) {
                    if( !isvisit[r][c] ) {
                        bfs(r,c,grid,BLUE);
                    }
                }
                // 탐색하려는 색이 빨간색
                else if( grid[r][c].equals(RED)) {
                    if( !isvisit[r][c] ) {
                        bfs(r,c,grid, RED);
                    }
                }
                // 탐색하려는 색이 초록색
                else if( grid[r][c].equals(GREEN) ) {
                    if( !isvisit[r][c] ) {
                        bfs(r,c,grid, GREEN);
                    }
                }
            }
        }

        out.append(count).append(" ");

        // 방문여부 체크 초기화
        isvisit = new boolean[n][n];
        count = 0;

        // 적록색약인 경우
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                // 탐색하려는 색이 파란색
                if( grid[r][c].equals(BLUE) ) {
                    if( !isvisit[r][c] ) {
                        bfs(r,c,grid,"B");
                    }
                }
                // 탐색하려는 색이 빨간색 or 초록색
                else if( grid[r][c].equals(RED) | grid[r][c].equals(GREEN)) {
                    if( !isvisit[r][c] ) {
                        bfs(r,c,grid,"RG");
                    }
                }
            }
        }

        out.append(count).append("\n");

        // 케이스를 주어진 경우
        /*
        StringTokenizer input = new StringTokenizer(in.readLine());
        int T = Integer.parseInt(input.nextToken());

        for(int t = 1; t <= T; t++) {

        }
        */
        System.out.println(out);
        long end = System.nanoTime();
        System.out.println((end - start) / 1_000_000_000 + "s");
        in.close();
    }

}
