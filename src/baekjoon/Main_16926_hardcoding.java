package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_hardcoding {

    private static int N;
    private static int M;
    private static int R;

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./res/baekjoon/arrayCycle1_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());           // N 입력
        M = Integer.parseInt(st.nextToken());           // M 입력
        R = Integer.parseInt(st.nextToken());           // R(회전수 입력)

        int[][] target = new int[N][M];
        // 회전할 배열 할당하기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < M; j++) {
                target[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 배열 확인
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(target[i][j]);
            }
            System.out.println();
        }

        //사이클 돌리기
        int[][] temp = cycleArray(target,0);

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(temp[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        in.close();
    }

    // 회전시키는 메서드
    private static int[][] cycleArray (int[][] arr ,int retation) {

        if( retation == R) {
            return arr;
        }

        int[][] tmp = new int[N][M];
        int start = 1;
        int end = 0;
        int sw = 1;
        int count_n = M - 1;
        int count_m = N - 1;
        int cycle = 0;
        while ( true ) {

            if( cycle == 4 ) {
                count_n -= 2;
                count_m -= 2;
                cycle = 0;
                start += sw;
                end += sw;
            }

            if ( count_n <= 0 || count_m <= 0) break;

            for(int i = 1; i <= count_n; i++ ) {
                tmp[end][start - sw] = arr[end][start];
                start += sw;
            }
            cycle++;
            start -= sw;
            end += sw;
            System.out.println(start +"::"+ end);

            for(int j = 1; j <= count_m; j++ ) {
                tmp[end - sw][start] = arr[end][start];
                end += sw;
            }

            cycle++;
            start-=sw;
            end-=sw;
            sw = -sw;
            System.out.println(start +"::"+ end);
        }

        return cycleArray(tmp,retation + 1);
    }
}
