package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023 {

    static int N;

    public static void main(String[] args) throws IOException {
        /* 1 <= N <= 8 */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in) );
        StringBuilder sb = new StringBuilder();                             // 출력을 저장할 공간
        N = Integer.parseInt(in.readLine());                                // 자릿수 입력
        int[] stock = {2,3,5,7};
        long start_time = System.nanoTime();

        if( N >= 2 ) {
            for (int i = 0; i < 4; i++) {
                for (int j = stock[i] * 10; j < (stock[i] + 1) * 10; j++) {
                    if( isPrimeNumber(j) ) {
                        getPrimeNumber(j, 2);
                    }
                }
            }
        } else {
            for(int num : stock) {
                System.out.println(num);
            }
        }

        long end_time = System.nanoTime();
        System.out.println(((end_time - start_time) / 1000000000) + "s");
    }

    private static void getPrimeNumber(int primeNumber, int dept) {
        if( dept >= N) {
            System.out.println(primeNumber);
            return;
        } else {
            for(int i = 1; i < 10; i++) {
                if( isPrimeNumber(primeNumber * 10 + i) ) {
                    getPrimeNumber(primeNumber * 10 + i, dept + 1    );
                }
            }
        }
    }

    private static boolean isPrimeNumber(int number) {

        if (number == 1) {
            return false;
        }

        if( number > 3 & ((number % 2 == 0) | (number % 3 == 0))) {
            return false;
        }

        for(int i = 2; i <= Math.sqrt(number); i++) {
            if( number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
