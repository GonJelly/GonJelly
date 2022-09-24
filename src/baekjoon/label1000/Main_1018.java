package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 체스판 다시 칠하기
// 시간 제한 2초
// 메모리 128 MB
public class Main_1018 {

    static int[] dy = {0,1,0,-1};
    static int[] dx = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int min = 9999999;
        // 가로 / 세로 크기
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        // 보드판의 크기 선언
        String[][] board = new String[r][c];

        // 보드판의 색칠된 부분 할당
        for( int y = 0; y < r; y++) {
            board[y] = in.readLine().split("");
//            System.out.println(Arrays.toString(board[y]));
        }

        String[][] tmp1 = new String[8][8];
        String[][] tmp2 = new String[8][8];

        for( int y = 0; y <= r - 8; y++) {
            for(int x = 0; x <= c - 8; x++) {
//                System.out.println("in");
                int row = y;
                int col = x;
                int count = 0;
                int count2 = 0;
//                String compare = board[y][x];

//                System.out.println("----------- 체스판 -----------");
                for(int i = row;  i < row +  8; i++) {
                    for(int j = col; j < col +  8; j++) {
//                        System.out.println(i + "<----- row | col ------> " + j + "color => "  + compare);
                        if( (i + j) % 2 == 0 ) {
                            if (!(board[i][j].equals("B"))) {
                                count++;
                            } else if (!(board[i][j].equals("W"))) {
                                count2++;
                            }
                        } else {
                            if (!(board[i][j].equals("W"))) {
                                count++;
                            } else if (!(board[i][j].equals("B"))) {
                                count2++;
                            }
                        }

//                        if( compare.equals("B")) {
//                            compare = "W";
//                        } else {
//                            compare = "B";
//                        }
                    }

//                    if( compare.equals("B")) {
//                        compare = "W";
//                    } else {
//                        compare = "B";
//                    }
                }
                int result = Math.min(count,count2);
                min = Math.min(min,result);

            }
        }

        System.out.println(min);
        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000_000_000 + "s");
    }

    private static int bfs(String[][] tmp,int r, int c) {

        boolean[][] visit = new boolean[8][8];
        Queue<int[]> queue = new LinkedList<>();
        // 왼쪽 맨위부터 시작
        queue.offer(new int[] {r,c});
        visit[r][c] = true;
        // 다시 색칠한 횟수;
        int count = 0;
        while( !queue.isEmpty() ) {

            int[] pos = queue.poll();

//            System.out.println(Arrays.toString(pos));
            for( int i = 0; i < 4; i++) {
                int nr = pos[0] + dy[i];
                int nc = pos[1] + dx[i];
                //  체스판의 범위를 벗어나지 않고 & 방문하지 않았다면
                if( visited(nr, nc) && !visit[nr][nc] ) {
                    // 같으면
                    if( tmp[pos[0]][pos[1]].equals(tmp[nr][nc]) ) {
                        // 다시 색칠하기
                        if( tmp[pos[0]][pos[1]].equals("B") ) {
                            tmp[nr][nc] = "W";
                        } else {
                            tmp[nr][nc] = "B";
                        }
                        ++count;
                    }
                    visit[nr][nc] = true;
                    queue.offer(new int[] {nr,nc});
                }
            }

        }

//        System.out.println(count);
        return count;

    }

    private static boolean visited(int r, int c) {
        if( r >= 0 && r < 8 && c >= 0 && c < 8 ) {
            return true;
        }
        return false;
    }


}
