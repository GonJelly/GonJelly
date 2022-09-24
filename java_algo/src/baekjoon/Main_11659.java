package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659 {

    public static void main(String[] args) throws IOException {

//        FileInputStream file = new FileInputStream("./res/sum_input1.txt");
//        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());              // 배열의 크기
        int t = Integer.parseInt(st.nextToken());              // 테스트 케이스 횟수
        int[] arrsum = new int[n + 1];                         // 누적합 저장 배열

        /* 누적 합 구하기 */
        st = new StringTokenizer(br.readLine());               // 배열 값 받아오기
        for(int i = 1; i <= n; i++ ) {
            arrsum[i] = arrsum[i - 1] + Integer.parseInt(st.nextToken());
        }
        /* 범위 누적 값 구하기 */
        StringBuilder sb2 = new StringBuilder();
        for( int i = 1; i <= t; i++ ) {;
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            /* 범위 누적 값 셋팅 */
            sb2.append(arrsum[j] - arrsum[k - 1]).append("\n");
        }
        /* 출력하기 */
        System.out.print(sb2);
    }
}
