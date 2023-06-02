package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17143_map {

    // 격자판의 상어 객체
    static class Shark{

        int x;  // 행의 위치
        int y;  // 열의 위치
        int s;  // 속도
        int d;  // 방향
        int z;  // 크기

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Shark{");
            sb.append(", x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", s=").append(s);
            sb.append(", d=").append(d);
            sb.append(", z=").append(z);
            sb.append('}');
            return sb.toString();
        }
    }
    // 상어의 이동방향 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽
    static int[][] dir = {
            {},{-1,0},{1,0},{0,1},{0,-1}
    };
    static int R,C,M;
    // Goal : 낚시꾼이 잡은 상어의 총 몸게
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/baekjoon/fisherToShark.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열
        M = Integer.parseInt(st.nextToken());   // 상어의 수

        // 격자판에 있는 상어를 표시하기 위해서
        Queue<Shark> queue = new ArrayDeque<>();
        Shark[][] board = new Shark[R + 1][C + 1];
        int fisher = 0; // 낚시꾼이 서있는 위치
        int weight = 0; // 낚시꾼이 잡은 상어의 총무게

        for( int cnt = 0; cnt < M; cnt++) {
            st = new StringTokenizer(in.readLine());
            while (st.hasMoreTokens()) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                Shark shark = new Shark(r, c, s, d, z);
//                shark.name = "상어" + String.valueOf(cnt);
                if ( board[r][c] == null ) {
                    board[r][c] = shark;
                } else {
                    if( board[r][c].z < shark.z ) {
                        board[r][c] = shark;
                    }
                }
            }
        }

        dfs(1,board,0);
    }

    private static void dfs(int person, Shark[][] board, int weight) {

        if( person > C) {
            System.out.println(weight);
            return;
        }

        // 낚시 시작
        for( int r = 1; r <= R; r++ ) {
            if( board[r][person] != null ) {
                weight += board[r][person].z;
                board[r][person] = null;
                break;
            }
        }
        // 잡히지 않은 상어를 저장할 공간
        Queue<Shark> queue = new ArrayDeque<>();
        // 격자판에 남아있는 상어 저장
        for( int r = 1 ; r <= R; r++ ) {
            for( int c = 1; c <= C; c++ ) {
                if( board[r][c] != null ) {
                    queue.offer(board[r][c]);
                }
            }
        }
        // 상어가 움직인 장소를 표시할 격자판 생성
        Shark[][] afterBD = new Shark[R + 1][C + 1];
        // 상어 움직인다.
        while( !queue.isEmpty() ) {

            Shark shark = queue.poll();
            // 상어의 스피드 만큼 칸을 이동한다.
            for( int speed = 1; speed <= shark.s; speed++) {

                int nx = shark.x + dir[shark.d][0];
                int ny = shark.y + dir[shark.d][1];
                // 격자판을 오버하게 된다면 방향을 반대로 이동
                if( nx == 0 || nx > R || ny == 0 || ny > C) {
                    // 상어의 방향이 위 or 오른쪽이면 +1
                    if( shark.d == 1 || shark.d == 3 ) shark.d += 1;
                        // 상어의 방향이 위 or 오른쪽이면 -1
                    else shark.d -= 1;
                    // 반대방향으로 이동
                    nx = shark.x + dir[shark.d][0];
                    ny = shark.y + dir[shark.d][1];
                }

                shark.x = nx;
                shark.y = ny;
            }
            // 어떤 상어도 도착하지 않았다면
            if( afterBD[shark.x][shark.y] == null ) {
                afterBD[shark.x][shark.y] = shark;
            }
            // 상어가 있다면 크기가 큰 상어가 차지한다.
            else {
                if( afterBD[shark.x][shark.y].z < shark.z ) {
                    afterBD[shark.x][shark.y] = shark;
                }
            }
        }

        dfs(person + 1, afterBD, weight);
    }
}
