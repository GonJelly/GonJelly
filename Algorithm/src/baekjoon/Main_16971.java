package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16971 {

    static int[][] arrayA_horizon, arrayA_verticle;
    static int max;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        // 행,열 값 받아오기
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // 배열A 할당하기
        arrayA_horizon = new int[n][m];
        arrayA_verticle = new int[m][n];
        max = 0;

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < m; c++) {
                arrayA_horizon[r][c] = arrayA_verticle[c][r] = Integer.parseInt(st.nextToken());
            }
        }
        // 바꾸지 않았을 경우에 배열B의 합을 구한다.
        getArrayB(n,m,arrayA_horizon);
        // 행,열의 위치를 변경했을 경우
        changeToArray(n,m,arrayA_horizon,arrayA_verticle);

        System.out.println(max);
    }

    // 열의 위치를 바꾼 배열을 만들어준다.
    private static void changeToArray(int n, int m, int[][] horizon, int[][] verticle) {

        // 열의 위치를 변경
        for(int r = 0; r < n - 1; r++) {
            for(int k = r + 1; k < n; k++) {
                int[][] array = copyByDubleArr(n, m, horizon);
                // 열 위치 변경 start
                int[] tmp = Arrays.copyOf(array[r],m);
                array[r] = array[k];
                array[k] = tmp;
                getArrayB(n,m,array);
//                printArr(n,array);
            }
        }

        for(int r = 0; r < m - 1; r++) {
            for(int k = r + 1; k < m; k++ ) {
                int[][] array = copyByDubleArr(m,n, verticle);
                int[] tmp = Arrays.copyOf(array[r],n);
                array[r] = array[k];
                array[k] = tmp;
                getArrayB(m,n,array);
//                printArr(m,array);
            }
        }

    }

    private static int[][] copyByDubleArr(int n, int m, int[][] array) {

        int[][] tmp = new int[n][m];
        for(int r = 0; r < n; r++) {
            tmp[r] = Arrays.copyOf(array[r],m);
        }
        return tmp;
    }

    private static void getArrayB(int n, int m, int[][] arrayA) {

        int sum = 0;
        for(int r = 0; r < n - 1; r++) {
            for( int c = 0; c < m - 1; c++) {
                sum += arrayA[r][c] + arrayA[r + 1][c] + arrayA[r][c + 1] + arrayA[r + 1][ c + 1];
            }
        }
        max = Math.max(max,sum);
    }

    private static void printArr(int n, int[][] array){
        System.out.println("==== 배열 출력 =====");
        for( int r = 0; r < n; r++ ) {
            System.out.println(Arrays.toString(array[r]));
        }
    }
}
