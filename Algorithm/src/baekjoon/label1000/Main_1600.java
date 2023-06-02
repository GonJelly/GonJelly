package baekjoon.label1000;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 시간제한 : 2초 메모리 : 256MB
// GOAL : 최소 이동횟수
// 시간초과 해결필요!!
public class Main_1600 {
    static class Monkey {
        int horse;  // 말처럼 뛸수 있는 횟수
        Point point; // 원숭이의 좌표
        int count;  // 뛴 횟수!!

        public Monkey(int horse, Point point , int count) {
            this.horse = horse;
            this.point = point;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "horse=" + horse +
                    ", point=" + point +
                    ", count=" + count +
                    '}';
        }
    }
    static int k, r, c,min, board[][];
    // 하, 상, 우, 좌
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    // 말의 움직임을 모방했을 때!!
    static int[] horseX = {-2,-2,2,2,1,-1,1,-1};
    static int[] horseY = {1,-1,1,-1,-2,-2,2,2};
    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();     // 시간체크
        FileInputStream file = new FileInputStream("./res/baekjoon/monkey.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        // 원숭이가 말의 움직임을 모방할 수 있는 횟수
        k = Integer.parseInt(st.nextToken());
        // 원숭이가 뛰는 횟수 저장 ( 최솟값 구하기 )
        min = Integer.MAX_VALUE;

        // 격자판의 크기
        st = new StringTokenizer(in.readLine());
        c = Integer.parseInt(st.nextToken());   // 가로
        r = Integer.parseInt(st.nextToken());   // 세로
        // 보드 선언 및 할당 ( 장애물 할당 )
        board = new int[r][c];
        for( int y = 0; y < r; y++) {
            st = new StringTokenizer(in.readLine());
            for( int x = 0; x < c; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
//            System.out.println(Arrays.toString(board[y]));
        }

        // 처음 원숭이 위치 초기화
        Monkey monkey = new Monkey(k,new Point(0,0),0);
        bfs(monkey);

        if( min != Integer.MAX_VALUE ) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }

        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000 + "ms");

    }

    private static void bfs(Monkey start) {

        Queue<Monkey> que = new LinkedList<>();
        que.offer(start);

        while ( !que.isEmpty() ) {

            Monkey monkey = que.poll();

            if( monkey.count > (r + c) - 2 ) {
                return;
            }
            // 말처럼 뛸 수 있으면 뛰기
            if( monkey.horse > 0) {
                for(int i = 0; i < 8; i++) {
                    int ny = monkey.point.x + horseX[i];
                    int nx = monkey.point.y + horseY[i];

                    if( isJump(ny,nx) ) {
                        que.offer(new Monkey(monkey.horse - 1,new Point(ny,nx), monkey.count + 1));
                        if( ny == r - 1 && nx == c - 1) {
                            min = monkey.count + 1;
                            return;
                        }
                    }
                }
            }

            // 기본 원숭이 처럼 이동하기
            for(int i = 0; i < 4; i++) {
                int ny = monkey.point.x + dx[i];
                int nx = monkey.point.y + dy[i];
                if( isJump(ny,nx) ) {
                    que.offer(new Monkey(monkey.horse,new Point(ny,nx),monkey.count + 1));
                    if( ny == r - 1 && nx == c - 1) {
                        min = monkey.count + 1;
                        return;
                    }
                }
            }

        }
    }
    private static boolean isJump(int y, int x) {
        // 격자판의 크기를 벗어나면 이동하지 않는다. and 이동하려는 곳에 장애물이 있으면 이동하지 않는다.
        if( y < 0 || y >= r || x < 0 || x >= c || board[y][x] == 1) {
            return false;
        }
        return true;
    }
}
