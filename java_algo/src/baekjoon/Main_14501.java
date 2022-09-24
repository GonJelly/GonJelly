package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준문제 : 퇴사 - 실버3
// 시간제한 : 2초 메모리 : 512MB
public class Main_14501 {
    // Goal : 최대이익을 구하기
    static int max;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        // 입력값 : N == 상담기간
        int n = Integer.parseInt(st.nextToken());
        max = -1;
        // 상담을 하는데 걸리는 거릴는 시간:t 금액:m
        // 요일의 숫자와 맞춰주기 위해서 n + 1
        int[] t = new int[n+1];
        int[] m = new int[n+1];

        for(int x = 1; x <= n; x++) {
            st = new StringTokenizer(in.readLine());
            t[x] = Integer.parseInt(st.nextToken());
            m[x] = Integer.parseInt(st.nextToken());
            // 확인
//            System.out.printf("%d일 : 상담시간 : %d , 금액 : %d%n",x,t[x], m[x]);
        }

        counseling(1,t,m,0);

        System.out.println(max);
    }
    // 상담시작 : ( 상담일자, 상담시간, 상담금액, 이익)
    private static void counseling(int idx, int[] t, int[] m,int total) {

        if( idx >= t.length ) {
            if( max < total) {
                max = total;
            }
            return;
        }

        // 상담을 받을 경우 ( 대신 N+1에는 퇴사를 하기때문에 전날까지 끝나는 상담만 가능 )
        if( idx + t[idx] <= t.length ) {
            counseling(idx + t[idx], t, m, total + m[idx]);
        }
        // 상담을 받지않을 경우
        counseling(idx + 1, t, m, total);
    }
}
