package baekjoon;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2636 {

    static int x,y;
    static int[][] direct = {
            {-1,0},{0,1},{1,0},{0,-1}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        x = Integer.parseInt(st.nextToken());   // 세로
        y = Integer.parseInt(st.nextToken());   // 가로

        int[][] board = new int[x][y];
        ArrayList<Point> cheese = new ArrayList<>();

        // 할당하면서 치즈의 위치를 저장
        for( int r = 0; r < x; r++) {
            st = new StringTokenizer(in.readLine());
            for( int c = 0; c < y; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if( board[r][c] == 1) {
                    cheese.add(new Point(r,c));
                }
            }
        }

        dfs(0,board,cheese,cheese.size());


    }

    private static void dfs(int cnt, int[][] board, ArrayList<Point> cheese, int cheeses) {

        // 치즈가 전부 녹으면 종료
        if( cheese.size() == 0 ) {
            System.out.println(cnt);
            System.out.println(cheeses);
            return;
        }
        // 녹기전 치즈의 갯수
        cheeses = cheese.size();
        // 치즈를 녹인다.
        bfs(board,cheese);
        // 시간 경과 (다음으로 이동)
        dfs(cnt + 1, board,cheese,cheeses);
    }

    private static void bfs(int[][] board, ArrayList<Point> cheese) {

        // 중복방문을 막기위해서 방문체크
        boolean[][] visit = new boolean[x][y];
        // 초기값 할당
        Queue<Point> air = new ArrayDeque<>();
        air.offer(new Point(0,0));
        visit[0][0] = true;
        // 공기랑 접촉되어 있는 치즈를 찾아내기
        while ( !air.isEmpty() ) {

            Point pos = air.poll();
            // 4방 탐색
            for( int i = 0; i < 4; i++ ) {
                int nx = pos.x + direct[i][0];
                int ny = pos.y + direct[i][1];
                // 판을 벚어나지 않고 방문한적이 없어야한다.
                if( isCheck(nx,ny) && !visit[nx][ny] ) {
                    visit[nx][ny] = true;
                    // 공기라면 주입
                    if( board[nx][ny] == 0 ) {
                        air.offer(new Point(nx,ny));
                    } 
                    // 치즈라면 녹여주고 주입하지는 않음
                    else {
                        board[nx][ny] = 0;
                        cheese.remove(new Point(nx,ny));
                    }
                }

            }
        }

    }

    private static boolean isCheck(int r, int c) {
        if( r < 0 || r >= x || c < 0 || c >= y) {
            return false;
        }
        return true;
    }
}
