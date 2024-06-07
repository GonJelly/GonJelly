package study.step3;

import java.util.Scanner;

public class SquareNumberTest {

    static long callExp1;
    static long callExp2;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int n = sc.nextInt();

        exp1(x, n);
        exp2(x, n);

        System.out.printf("exp1 호출 횟수 : %d , exp2 호출 횟수 : %d",callExp1, callExp2);
    }

    static long exp1( long x , long n ) {
        callExp1++;
        if( n == 1) return x;
        long y = exp1( x , n / 2 );
        return n % 2 == 0 ? y * y : y * y * x;
    }

    static long exp2( long x , long n ) {
        callExp2++;
        if( n == 1 ) return x;
        return x * exp2( x , n - 1 );
    }
}
