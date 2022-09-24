package swea.D4;

import java.io.*;
import java.util.Arrays;

/* 사다리 문제 */
public class D4_1210 {

    public static void main(String[] args) throws IOException{
        FileInputStream file = new FileInputStream("./study_algorithm/res/ladder_input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 1; t <= 10; t++) {
            int tc = Integer.parseInt(br.readLine());
            /* 사다리 크기 100x100 */
            int[][] ladder = new int[100][100];
            /* 시작지점 저장 공간 */
            int start = 0;

            /* 사다리 할당 */
            for(int i = 0; i < 100; i++) {
                ladder[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            /* 사다리 타기 start */
            for(int k = 0; k < 100; k++) {
                /* start 지점이 1이여야 시작 */
                if( ladder[0][k] == 1 && BFS(ladder,k,0,k,0) == 2 ) {
                    start = k;
                }
            }
            /* 사다리 타기 end */

            System.out.printf("#%d %d%n",tc, start);

        }
    }

    private static int BFS(int[][] ladder,int start, int x, int y, int way) {

        /* 마지막에 도달하면 목표 점검 */
        if( x == 99 ) {
            if( ladder[x][y] == 2) {
                return 2;
            } else {
                return 0;
            }
        }
        /* 범위를 벗어나지 않으면서 사다리가 이어져있고 진행방향이 일치하면 진행 */
        if( y - 1 >= 0 && ladder[x][y-1] == 1 && way != 1) {
            return BFS(ladder,start, x, y-1 , -1);
        }

        /* 범위를 벗어나지 않으면서 사다리가 이어져있고 진행방향이 일치하면 진행 */
        if( y + 1 < ladder[x].length && ladder[x][y+1] == 1 && way != -1) {
            return BFS(ladder,start, x, y+1, 1);
        }
        /* 왼쪽 / 오른쪽 모두 사다리가 이어져있지않으면 아래로 진행 */
        return BFS(ladder,start,x+1, y,0);
    }
}
