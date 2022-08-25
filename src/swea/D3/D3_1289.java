package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_1289 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder in = new StringBuilder(br.readLine());
        // 테스트 케이스 할당
        int T = Integer.parseInt(in.toString());

        for( int t = 1; t <= T; t++) {

            in = new StringBuilder(br.readLine());
            int count = 0;                                  // 변환 횟수
            char[] origin = in.toString().toCharArray();    // 원래 메모리 값할당
            /* 초기화 상태가 모두 0 bit */
            /* 만약 원본 메모리 값이 1이면 먼저 변환 */
            if( origin[0] == '1') {
                count++;
            }
            /* for문을 이용한 풀이 */
            /* 숫자가 전환되는 시점에 변환 1회 증가
            for(int i = 0; i < origin.length - 1; i++) {
                if( origin[i] != origin[i+1]) {
                    count++;
                }
            }
            */

            System.out.printf("#%d %d%n", t, recoveryMM(origin, count, 0));
        }
    }

    /* 재귀를 이용한 풀이 */
    private static int recoveryMM(char[] origin, int count, int select) {

        if( origin.length - 1 == select) {
            return count;
        }

        if( origin[select] != origin[select + 1] ) {
            return recoveryMM(origin, ++count, ++select);
        } else {
            return recoveryMM(origin, count, ++select);
        }
    }
}
