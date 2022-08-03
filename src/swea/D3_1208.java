package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_1208 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for( int t = 1; t <= 10; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            /* 덤프의 횟수 */
            int dump = Integer.parseInt(st.nextToken());
            /* 상자의 보관고 선언 */
            int[] boxing = new int[100];
            /* 상자의 높이 할당 */
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 100; i++) {
                boxing[i] = Integer.parseInt(st.nextToken());
            }

            System.out.printf("#%d %d%n",t,dumped(boxing, dump));
        }

    }

    private static int dumped(int[] boxing, int dump) {

        int[] temp = Arrays.copyOf(boxing,100);
        int max = Arrays.stream(temp).max().getAsInt();
        int min = Arrays.stream(temp).min().getAsInt();

        /* 덤프 횟수가 없으면 종료 */
        if( dump == 0 ) {
            return Math.abs(max - min);
        }

        /* 평탄화 작업이 끝나면 종료 */
        if ( Math.abs(max - min) <= 1) {
            return Math.abs(max - min);
        }
        // 최고점 상자 빼기
        for(int i = 0; i < 100; i++) {
            if (max <= temp[i]) {
                temp[i] -= 1;
                break;
            }
        }

        // 최저점 상자 더하기
        for(int j = 0; j < 100; j++) {
            if (min >= temp[j]) {
                temp[j] += 1;
                break;
            }
        }

        return dumped(temp,--dump);
    }
}
