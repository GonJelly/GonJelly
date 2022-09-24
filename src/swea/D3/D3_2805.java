package swea.D3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class D3_2805 {

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./study_algorithm/res/farm_input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for ( int test_case = 1; test_case <= T; test_case++) {

            int n = Integer.parseInt(br.readLine());    // 농장의 크기
            int mid = n/2;                              // 중간값 구하기
            int sum = 0;                                // 총 수확한 농산물
            int[][] farm = new int[n][n];               // 농장 선언
            // 농작물 할당하기
            for( int i = 0; i < n; i++) {
                farm[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }
            // 농산물 수확하기
            sum = isFarm(farm, mid, 0, n);

            System.out.println("#" + test_case + " " + sum);
        }

    }

    private static int isFarm(int[][] farm,int col, int start, int end) {
        // 농장의 범위를 벗어나면 종료
        if( start >= end || col < 0 || col > farm.length) {
            return 0;
        }

        if( start < end) {
            int sum = 0;                                        // 농장물의 합
            int mid = farm.length/2;                            // 중간값
            for ( int i = start; i < end; i++) {
                sum += farm[col][i];
            }
            // 중간구간부터 위 아래로 늘려가면서 수확물의 합을 리턴
            if( mid == col ){
                sum += isFarm(farm, col + 1, start + 1, end - 1);
                sum += isFarm(farm, col - 1, start + 1, end - 1);
            } else if ( mid > col ) {
                sum += isFarm(farm, col - 1, start + 1, end - 1);
            } else if ( mid < col ) {
                sum += isFarm(farm, col + 1, start + 1, end - 1);
            }

            return sum;
        }
        return 0;
    }
}
