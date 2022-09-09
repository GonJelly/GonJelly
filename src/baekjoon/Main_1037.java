package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1037 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        int max = -1;
        int min = Integer.MAX_VALUE;
        // 모든 숫자를 받아온다.
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            // 제인 큰수를 구한다.
            int tmp = Integer.parseInt(st.nextToken());
            if( max < tmp) {
                max = tmp;
            }
            // 제일 작은 수를 구한다.
            if( min > tmp) {
                min = tmp;
            }
        }
        // 제일 큰 수와 작은 수를 곱해준다.!!
        int origin = max * min;
        System.out.println(origin);
    }
}
