package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10870 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        System.out.println(fibonacci(t));

    }

    private static int fibonacci(int n) {
        if( n == 0) {
            return 0;
        } else if( n == 1) {
            return 1;
        }

        return fibonacci(n-2) + fibonacci( n -1);
    }
}
