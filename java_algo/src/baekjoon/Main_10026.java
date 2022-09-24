package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10026 {

    private static final String RED = "R";
    private static final String GREEN = "G";
    private static final String BLUE = "B";

    private static void dfs(int r, int c,String[][] map,String color, boolean[][] visit) {

        visit[r][c] = true;
        // 4방향 탐색 start
        for(int i = 0; i < 4; i++) {
            int nr = r + direction[i][0];
            int nc = c + direction[i][1];
            // 범위를 벗어나면 다음탐색
            if( isCheck(nr,nc) ) continue;
            // 탐색한 적이 없고 탐색하려는 색과 동일하면 실행
            if( !visit[nr][nc] && color.contains(map[nr][nc]) ) {
                visit[nr][nc] = true;
                dfs(nr,nc,map,color,visit);
            } // 조건과 일치하면 추가 end
        } // 방향 탐색 end


    }

    private static boolean isCheck(int r, int c) {
        if( r >= 0 && r < n && c >= 0 && c < n) {
            return false;
        } else {
            return true;
        }
    }

    private static int n;
    private static int[][] direction = {
            {-1,0},{0,1},{1,0},{0,-1}
    };
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(in.readLine());
        StringBuilder out = new StringBuilder();
        long start = System.nanoTime();
        int cnt1 = 0, cnt2 = 0;
        // 그리드의 크기 입력
        n = Integer.parseInt(input.nextToken());
        // 그룹 초기화

        // 그리드 선언
        String[][] grid = new String[n][n];

        // 그리드 할당
        for(int x = 0; x < n; x++ ) {
            grid[x] = in.readLine().split("");
//            System.out.println(Arrays.toString(grid[x]));
        }

        // 방문여부 체크 초기화
        boolean[][] blind = new boolean[n][n];
        boolean[][] nonBlind = new boolean[n][n];

        // 적록색약가 아닌 경우
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                // 탐색하려는 색이 파란색
                if( grid[r][c].equals(BLUE) ) {
                    if( !blind[r][c] ) {
                        dfs(r,c,grid,BLUE,blind);
                        cnt1++;
                        cnt2++;
                    }
                }

                // 탐색하려는 색이 빨간색
                else if( grid[r][c].equals(RED) | grid[r][c].equals(GREEN) ) {
                    if( !blind[r][c] & grid[r][c].equals(RED) ) {
                        dfs(r,c,grid, RED,blind);
                        cnt1++;
                    } else if ( !blind[r][c] & grid[r][c].equals(GREEN) ){
                        dfs(r,c,grid, GREEN,blind);
                        cnt1++;
                    }

                    if( !nonBlind[r][c] ) {
                        dfs(r,c,grid,RED + GREEN,nonBlind);
                        cnt2++;
                    }
                }
            }
        }

        out.append(cnt1).append(" ").append(cnt2).append("\n");

        System.out.print(out);
        long end = System.nanoTime();
        System.out.println((end - start) / 1_000_000_000 + "s");
        in.close();
    }
}
