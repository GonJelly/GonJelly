package baekjoon;

import java.util.Scanner;

public class Main_1010 {

    private static long[] result;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int r = in.nextInt();
            int n = in.nextInt();
            long count = 0;
            result = new long[n+1];

            for(int i = r; i <= n; i++) {
                count = dp(i,r);
            }

            System.out.println(count);
        }
    }

    private static long dp(int x, int r) {

        if ( x <= r) {
            return result[x] = 1;
        }
        return result[x] = result[x - 1] * x / (x - r);

    }
}
