package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 스타트와 링크
public class Main_14889 {

    // 입력받는 값 저장할 N, S 선언
    private static int N, min;       // 선수의 수를 저장할 변수, 최솟값을 저장할 변수
    private static int[][] S;   // 선수의 시너지를 저장할 변수
    private static boolean[] isSelected;    // 스타트 팀의 선정된 선수를 확인하는 배열

    public static void main(String[] args) throws IOException {

        // 입력값을 받을 reader
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // 전역변수 메모리 할당
        N = Integer.parseInt(input.readLine());
        min = Integer.MAX_VALUE;
        S = new int[N][N];
        isSelected = new boolean[N];

        // space로 수를 구분하기 때문에 StringToken을 사용
        // 선수들이 같은 팀이 되었을 때 발휘할 수 있는 값 할당
        // 시간복잡도 계산 O(N^2) worst => 400ns
        for( int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            for( int c = 0; c < N; c++) {
                S[r][c] = Integer.parseInt(st.nextToken());
            }
            // 할당된 값 확인
//            System.out.println(Arrays.toString(S[r]));
        }

        dfs(0,0);

        System.out.println(min);
    }

    private static void dfs(int idx, int cnt) {

        // 기저조건 ( 만약 전체선수의 N/2 만큼 선택할 경우 종료 )
        // N / 2 만큼 선택은 하지 않았지만 선택할 선수가 없을 경우에도 종료
        if( idx == N) {
            // N / 2 만큼 선수가 나눠졌을 경우에는 시너지 계산!!
            if( N / 2 == cnt ) {
                // 초기화
                int start = 0;
                int link = 0;

                // 팀 합산 계산
                for( int r = 0; r < N; r++ ) {
                    for( int c = 0; c < N; c++) {
                        if( isSelected[r] && isSelected[c] ) {
                            start += S[r][c];
                        }
                        if( !isSelected[r] && !isSelected[c] ) {
                            link += S[r][c];
                        }
                    }
                }
                min = Math.min(min,Math.abs(start - link));
//                System.out.printf("start팀 : %d , link팀 : %d, 차이 : %d %n",start,link,Math.abs(start - link));
            }
            return;
        }

        // 선택을 했을 경우
        isSelected[idx] = true;
        dfs(idx + 1, cnt + 1);

        // 선택을 하지 않았을 경우
        isSelected[idx] = false;
        dfs( idx + 1, cnt);
    }
}
