package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D5_3238 {

    static long mod;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= TC; tc++ ) {
            st = new StringTokenizer(in.readLine());

            long N = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());
            mod = Long.parseLong(st.nextToken());

            long[] fac = new long[(int) mod + 1];
            fac[0] = 1;
            for(int i = 1; i <= (int) mod; i++) {
                fac[i] = (fac[i - 1] * i) % mod;
            }
            long ans = 1;
            while (N > 0 && R > 0 ) {

                int n = (int) (N % mod);
                int r = (int) (R % mod);

                if( r > n ) {
                    ans = 0;
                    break;
                }

                ans = ans * fac[n] % mod;
                long ref = (fac[r] * fac[n-r]) % mod;
                ans = ans * fermat(ref,mod - 2) % mod;

                N /= mod;
                R /= mod;
            }

            ans %= mod;
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static long fermat(long n, long x) {
        if( x == 0) return 1;
        long tmp = fermat( n, x / 2);
        long ref = (tmp * tmp) % mod;
        if( x % 2 == 0) return ref;
        else return (ref * n) % mod;
    }

}
