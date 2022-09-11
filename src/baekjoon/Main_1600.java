package baekjoon;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 시간제한 : 2초 메모리 : 256MB
// GOAL : 최소 이동횟수
// 시간초과 해결필요!!
public class Main_1600 {
    static class Monkey {
        int horse;
        Point point;

        public Monkey(int horse, Point point ) {
            this.horse = horse;
            this.point = point;
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "horse=" + horse +
                    ", point=" + point +
                    '}';
        }
    }
    static int k, w, h,count, board[][];
    // 하, 상, 우, 좌
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    // 말의 움직임을 모방했을 때!!
    static int[] horseX = {-2,-2,2,2,1,-1,1,-1};
    static int[] horseY = {1,-1,1,-1,-2,-2,2,2};
    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        FileInputStream file = new FileInputStream("./res/baekjoon/monkey.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        // 원숭이가 말의 움직임을 모방할 수 있는 횟수
        k = Integer.parseInt(st.nextToken());
        count = Integer.MAX_VALUE;
        st = new StringTokenizer(in.readLine());
        Monkey monkey = new Monkey(k,new Point(0,0));

        // 격자판의 크기
        w = Integer.parseInt(st.nextToken());   // 가로
        h = Integer.parseInt(st.nextToken());   // 세로
        // 보드 선언 및 할당
        board = new int[h][w];
        boolean[][] visit = new boolean[h][w];
        for( int y = 0; y < h; y++) {
            st = new StringTokenizer(in.readLine());
            for( int x = 0; x < w; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
            System.out.println(Arrays.toString(board[y]));
        }

        move(0,monkey,visit);

        if( count != Integer.MAX_VALUE) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
        long end = System.nanoTime();
        System.out.println(( end - start ) / 1_000_000_000 + "s");
    }
    // 움직인 횟수
    private static void move(int cnt, Monkey monkey, boolean[][] visit) {

        System.out.println(monkey);
        visit[monkey.point.x][monkey.point.y] = true;

        if( count < cnt ) {
            return;
        }

        // 맨 오른쪽 아래 도착하면 종료!!
        if( monkey.point.x == w-1 && monkey.point.y == h-1) {
            System.out.println("종료");
            if( count > cnt) {
                count = cnt;
            }
            return;
        }
        System.out.println("------- 말처럼 움직인다. -------");
        // 말의 움직임을 모방할 수 있다면 먼저 말처럼 이동
        if( monkey.horse != 0 ) {
            for (int t = 0; t < 8; t++) {
                int tmpX = monkey.point.x + horseX[t];
                int tmpY = monkey.point.y + horseY[t];
                // 범위를 벗어나지 않고 and 방문한 적이 없어야한다.
                if (isJump(tmpX, tmpY) && !visit[tmpX][tmpY] ) {
                    System.out.printf("x : %d y : %d   true%n",tmpX,tmpY);
                    move(cnt + 1, new Monkey(monkey.horse - 1,new Point(tmpX,tmpY)), visit);
                }
            }
        }

        System.out.println("------- 원숭이 움직이다. --------");
        // 원숭이 자신의 움직일 수 있는 범위를 이동한다.
        for (int t = 0; t < 4; t++) {
            int tmpX = monkey.point.x + dx[t];
            int tmpY = monkey.point.y + dy[t];

            if (isJump(tmpX, tmpY) && !visit[tmpX][tmpY]) {
                System.out.println("true");
                move(cnt + 1, new Monkey(monkey.horse, new Point(tmpX, tmpY)), visit);
            }
        }

        visit[monkey.point.x][monkey.point.y] = false;  // 백트래킹
    }

    private static boolean isJump(int x, int y) {
        // 격자판의 크기를 벗어나면 이동하지 않는다. and 이동하려는 곳에 장애물이 있으면 이동하지 않는다.
        if( y < 0 || y >= h || x < 0 || x >= w || board[y][x] == 1) {
            return false;
        }
        return true;
    }
}
