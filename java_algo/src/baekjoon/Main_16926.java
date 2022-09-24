package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926 {

    private static int N,M,R;
    private static int[][] target;

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./res/baekjoon/arrayCycle1_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());           // N 입력
        M = Integer.parseInt(st.nextToken());           // M 입력
        R = Integer.parseInt(st.nextToken());           // R(회전수 입력)

        target = new int[N][M];
        // 회전할 배열 할당하기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < M; j++) {
                target[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //사이클 돌리기 R번만큼
        for(int i = 1; i <= R; i++) {
            rotation(0, 0);
        }

        for(int[] arr : target) {
            for(int i = 0; i < M; i++) {
                sb.append(arr[i]);
            }
            sb.append("\n"); // 개행
        }
        System.out.println(sb);
        in.close();
    }
    private static void rotation( int x, int y) {

            int layer = 0;
            int rotation_cnt = 0;
            while ( true ) {
                if( rotation_cnt == 2) break;     // 회전할 층을 전부하면 종료
                int temp = target[x][y]; //
                for(int i = y+1; i < M - layer; i++ ) {target[x][i-1] = target[x][i]; y++;}
                for(int i = x+1; i < N - layer; i++ ) {target[i-1][y] = target[i][y]; x++;}
                for(int i = y-1; i >= layer; i-- ) {target[x][i+1] = target[x][i]; y--;}
                for(int i = x-1; i > layer; i-- ) {target[i+1][y] = target[i][y]; x--;}
                target[x][y++] = temp;
                rotation_cnt++;
                layer++;
            }
    }
}
