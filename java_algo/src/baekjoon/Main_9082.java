package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9082 {

    static int n, max;
    static int[] counting;
    static char[] mine;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= TC; tc++) {

            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken()); // 1 <= n <= 100
            max = 0;
            counting = new int[n];
            mine = new char[n];
            dp = new int[n];

            char[] tmp = in.readLine().toCharArray();
            for(int i = 0; i < tmp.length; i++) {
                counting[i] = tmp[i] - '0';
            }

            mine = in.readLine().toCharArray();

            isCheck(0,0);
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }

    private static void isCheck(int cnt, int sum) {

        if( cnt == n ) {
            // 설치한 지뢰를 입력값과 비교
            for(int i = 0; i < n; i++) {
                // 맞지 않으면 종료
                if( dp[i] != counting[i])
                    return;
            }
            // 최댓값 비교
            max = Math.max(max,sum);
            return;
        }

        if( mine[cnt] == '#' ) {
            // 범위에 맞게 확인
            if( cnt - 1 == -1 ) {
                // 만약 설치했을 때 지뢰의 수를 벗어나면 false
                if( install(cnt, cnt + 1) ) {
                    // 지뢰를 설치한다.
                    isCheck(cnt + 1, sum + 1);
                }
                // 지뢰를 설치하지 않는다.
                isCheck(cnt + 1, sum);
            } else if( cnt + 1 == n ) {
                // 만약 설치했을 때 지뢰의 수를 벗어나면 false
                if( install(cnt - 1, cnt) ) {
                    // 지뢰를 설치한다.
                    isCheck(cnt + 1, sum + 1);
                }
                // 지뢰를 설치하지 않는다.
                isCheck(cnt + 1, sum);
            } else {
                // 만약 설치했을 때 지뢰의 수를 벗어나면 false
                if( install(cnt - 1,cnt, cnt + 1 ) ) {
                    // 지뢰를 설치한다.
                    isCheck(cnt + 1, sum + 1);
                }
                // 지뢰를 설치하지 않는다.
                isCheck(cnt + 1, sum);
            }
        } else {
            // 지뢰가 있다고 가정한다.
            if( cnt - 1 == -1 ) {
                if( install(cnt, cnt + 1) )
                    isCheck(cnt + 1, sum + 1);
            } else if( cnt + 1 == n ) {
                if( install(cnt - 1, cnt) )
                    isCheck(cnt + 1, sum + 1);
            } else {
                if( install(cnt - 1,cnt, cnt + 1 ) )
                    isCheck(cnt + 1, sum + 1);
            }
        }
    }

    private static boolean install(int ... cnt){
        for(int idx : cnt) {
            dp[idx]++;
        }

        for( int idx : cnt ) {
            if( counting[idx] < dp[idx]) {
                for( int reset : cnt) {
                    dp[reset]--;
                }
                return false;
            }
        }

        return true;
    }

    private static boolean isvaliable(int cnt) {
        if( cnt >= 0 && cnt < n ) return true;
        else return false;
    }
}
