package baekjoon.label3000;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_3055_bfs2 {

    static class Node {
        int x;  // 행 좌표
        int y;  // 열 좌표
        int day;// 날짜

        public Node(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    // 위, 아래, 오른쪽, 왼쪽
    private static int[][] dir = { {-1,0},{1,0},{0,1},{0,-1} };
    private static int R,C;
    private static Queue<Node> wave;
    private static Queue<Node> erin;
    private static Point goal;
    private static boolean[][] erinCK, waveCK;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        R = Integer.parseInt(st.nextToken());   // 행의 크기
        C = Integer.parseInt(st.nextToken());   // 열의 크기

        wave = new ArrayDeque<>();          // 홍수의 좌표 넣기
        erin = new ArrayDeque<>();          // 고슴도치의 좌표 넣기
        goal = null;                        // 비버집 좌표
        erinCK = new boolean[R][C];         // 고슴도치 이동여부
        waveCK = new boolean[R][C];         // 물 이동여부

        // 맵 할당 및 홍수 && 비버 좌표 구하기
        for(int r = 0; r < R; r++) {
            String[] input = in.readLine().split("");
            for(int c = 0; c < C; c++) {
                // 돌이거나 물이면 방문할 수 없도록 처리
                switch( input[c] ) {
                    case "S":
                        erinCK[r][c] = true;
                        erin.offer(new Node(r,c,0));
                        break;
                    case "*":
                        waveCK[r][c] = true;
                        wave.offer(new Node(r,c,0));
                        break;
                    case "X":
                        erinCK[r][c] = true;
                        waveCK[r][c] = true;
                        break;
                    case "D":
                        goal = new Point(r,c);
                        waveCK[r][c] = true;
                        break;
                }
            }
        }

        BFS();


    }

    private static void BFS() {

        waveBFS();
        int current = 0;

        while( !erin.isEmpty() ){
            Node node = erin.poll();
            int r = node.x;
            int c = node.y;
            int time = node.day;
            // 물 이동
            if( current < time ) {
                waveBFS();
                current = time;
            }

            for( int i = 0; i < 4; i++ ) {

                int nx = r + dir[i][0];
                int ny = c + dir[i][1];
                // 범위를 벗어나면 continue;
                if( valiable( nx, ny) ) continue;

                // 비버집 도착
                if( goal.x == nx && goal.y == ny ) {
                    System.out.println(time + 1);
                    return;
                }
                // 고슴도치가 이동하지 않고 || 파도가 아직 오지 않았다면!!
                if( !erinCK[nx][ny] && !waveCK[nx][ny] ) {
                    erinCK[nx][ny] = true;
                    erin.offer(new Node(nx,ny, time + 1));
                }

            }
        }

        System.out.println("KAKTUS");
    }

    private static void waveBFS() {

        int size = wave.size();

        for ( int i = 0; i < size; i++ ) {

            Node node = wave.poll();
            int r = node.x;
            int c = node.y;
            int time = node.day;

            for( int j = 0; j < 4; j++ ) {
                int nx = r + dir[j][0];
                int ny = c + dir[j][1];

                if( valiable(nx, ny) ) continue;

                if( !waveCK[nx][ny] ) {
                    waveCK[nx][ny] = true;
                    wave.offer(new Node(nx,ny,time + 1));
                }
            }
        }
    }



    private static boolean valiable(int ny , int nx) {
        // 떠텁숲의 범위를 벗어났거나 돌이거나 소굴이면 물은 이동하지않음
        if (ny < 0 || ny >= R || nx < 0 || nx >= C) return true;
        return false;
    }
}
