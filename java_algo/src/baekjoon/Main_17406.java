package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17406 {

    private static int N,M,R;
    private static int[] r,c,s;
    private static boolean[] isChecked;
    private static int globalMin = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./study_algorithm/res/baekjoon/arrayCycle4_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());       // 배열의 행
        M = Integer.parseInt(st.nextToken());       // 배열의 열
        R = Integer.parseInt(st.nextToken());       // 연산의 횟수
        isChecked = new boolean[R];                     // 연산이 이루어 졌는지 확인
        // 연산에 사용되는 r,c 가 1부터 시작하여 가독성을 위해 +1 하였습니다.
        // 배열 값 할당 start
        int[][] target = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 1; j <= M; j++) {
                target[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 배열 값 할당 end
        // 연산 할당하기 start
        r = new int[R];       // 연산 행
        c = new int[R];       // 연산 열
        s = new int[R];       // 연산 값
        // 연산 값 할당
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(in.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }
        // 연산 할당하기 end

        // 연산 start
        operator(target,0);
        System.out.println(globalMin);
    }

    private static void operator(int[][] target, int cnt) {

        if( cnt == R ) {
            arrayMin(target);
//            print(target);
            return;
        }

        for(int i = 0; i < R; i++) {
            if( isChecked[i] == false ) {
                isChecked[i] = true;
                int[][] temp = rotate(r[i],c[i],s[i], target);
                operator(temp,cnt + 1);
                isChecked[i] = false;
            }
        }
    }

    private static void arrayMin(int[][] target) {

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++) {
            int sum = 0;
            for(int j = 1; j <= M; j++) {
                sum += target[i][j];
            }
            min = Math.min(min,sum);
        }
        globalMin = Math.min(globalMin,min);
    }

    /**
     *
     * @param r 행
     * @param c 열
     * @param s 연산값
     * @param target 시계방향으로 돌릴 배열
     * @return 시계방향으로 회전시킨 배열을 반환해준다.
     */
    private static int[][] rotate(int r, int c, int s, int[][] target) {
        int[][] temp = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                temp[i][j] =  target[i][j];
            }
         }
        int x1 = r - s;         // 왼쪽 위 x좌표
        int y1 = c - s;         // 왼쪽 위 y좌표
        int x2 = r + s;         // 오른쪽 아래 x좌표
        int y2 = c + s;         // 오른쪽 아래 y좌표
        int rotation = 0;
        while( true ) {
            if( rotation == s) break;
            int tmp = temp[x1][y1];
            for(int i = x1+1; i <= x2; i++) { temp[i-1][y1] = temp[i][y1]; }
//            System.out.println(x1 + ":::" + y1 + ":::" + x2 + "::" + y2);
            for(int i = y1+1; i <= y2; i++) { temp[x2][i-1] = temp[x2][i]; }
//            System.out.println(x1 + ":::" + y1 + ":::" + x2 + "::" + y2);
            for(int i = x2-1; i >= x1; i--) { temp[i+1][y2] = temp[i][y2]; }
//            System.out.println(x1 + ":::" + y1 + ":::" + x2 + "::" + y2);
            for(int i = y2-1; i >= y1; i--) { temp[x1][i+1] = temp[x1][i]; }
//            System.out.println(x1 + ":::" + y1 + ":::" + x2 + "::" + y2);
            temp[x1][y1+1] = tmp;
            x1++; y1++; x2--; y2--;
            rotation++;
        }
//        print(temp);
        return temp;
    }

    private static void print(int[][] array) {
        System.out.println("-----------------------------");
        for(int[] tmp : array) {
            System.out.println(Arrays.toString(tmp));
        }
        System.out.println("-----------------------------");
    }
}
