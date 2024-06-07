package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15961 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int count = 0;
        int max = 0;

        int n = Integer.parseInt(st.nextToken());   // 접시의 수
        int d = Integer.parseInt(st.nextToken());   // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken());   // 연속해서 먹은 초밥의 수
        int c = Integer.parseInt(st.nextToken());   // 쿠폰번호

        int[] sushi = new int[n];   // 접시 위에 있는 초밥의 종료
        int[] eat = new int[d + 1]; // 먹을 수 있는 초밥의 종류
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            sushi[i] = Integer.parseInt(st.nextToken());
        }
        // 처음부터 먹을 수 있는 만큼 먹는다.
        for( int i = 0; i < k; i++) {
            if ( eat[sushi[i]] == 0 ) {
                count++;
            }
            eat[sushi[i]]++;
        }

        max = count;

        for( int j = 1; j < n; j++) {

            if ( max <= count ) {
                // 쿠폰에 있는 초밥을 먹지않았다면
                if( eat[c] == 0 ) {
                    max = count + 1;
                }
                // 쿠폰에 있는 초밥을 먹었다면
                else {
                    max = count;
                }
            }

            int end = ( j + k - 1 ) % n;
            if( eat[sushi[end]] == 0 ) {
                count++;
            }
            eat[sushi[end]]++;

            eat[sushi[j - 1]]--;
            if( eat[sushi[j - 1]] == 0) {
                count--;
            }
        }
        System.out.println(max);
    }
}
