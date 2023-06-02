package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_5607 {

    static long[] memo;
    private static final long MOD = 1234567891;
    static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= TC; tc++) {

            count = 0;

            st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            memo = new long[n + 1];
            memo[0] = 1;

            for(int i = 1; i <= n; i++) {
                memo[i] = (memo[i - 1] * i) % MOD;
            }

            long bot = (memo[r] * memo[n - r]) % MOD;
            long refBot = fermat(bot, MOD - 2);

            long ans = (memo[n] * refBot) % MOD;
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static long fermat(long n, long x) {

        if( x == 0) return 1;
        long tmp = fermat(n, x / 2);
        long ref = (tmp * tmp) % MOD;
        if( x % 2 == 0) return ref;
        else return (ref * n) % MOD;
    }
}
