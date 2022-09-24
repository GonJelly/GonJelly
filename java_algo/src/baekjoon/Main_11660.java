package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660 {

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/sum_input2.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());   // 배열의 크기
        int m = Integer.parseInt(st.nextToken());   // 테스트 케이스의 횟수

        int[][] arr = new int[n + 1][n + 1];        // 원본 배열
        int[][] arrsum = new int[n + 1][n + 1];     // 행 누적 합 구하는 배열

        // 배열에 값을 할당해준다.
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 행마다 누적된 값을 갖는 배열을 재생성 해준다.
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                arrsum[i][j] += arrsum[i][j - 1] + arr[i][j];
            }
        }

//        getArray(arr);
//        getArray(arrsum);

        for(int k = 1; k <= m; k++) {

            int sum = 0;
            st = new StringTokenizer(in.readLine());

            int x1 = Integer.parseInt(st.nextToken());  // x1좌표
            int y1 = Integer.parseInt(st.nextToken());  // y1좌표
            int x2 = Integer.parseInt(st.nextToken());  // x2좌표
            int y2 = Integer.parseInt(st.nextToken());  // y2좌표

            // 범위에 있는 누적 합을 모두 더하기
            for(int i = x1; i <= x2; i++) {
                sum += arrsum[i][y2] - arrsum[i][y1 - 1];
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }

//    private static void getArray(int[][] arr) {
//        for(int[] temp : arr) {
//            for(int number : temp) {
//                System.out.print(number);
//            }
//            System.out.println();
//        }
//    }
}
