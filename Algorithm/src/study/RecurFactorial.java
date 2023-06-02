package study;

public class RecurFactorial {

    static int res = 1;

    // 재귀를 사용하지 않고 재귀를 구현! ( 반복문 사용 )
    public static int factorial1(int n) {
        // 5 * 4 * 3 * 2 * 1
        int res = 1;
        for( int i = n; i > 1; i-- ) {
            res *= i;
        }
        return res;
    }

    // 팩토리얼을 사용해서 결과값 출력하기 ( 반환값이 없기 때문에 결과 값은 전역멤버로 선언해서 사용 )
    public static void factorial2(int n) {
        // 기저조건
        if( n < 1 ) return;

        res *= n;
        factorial2(--n);

    }

    // return 문을 사용해서 값을 반환하는 팩토리얼
    public static int factorial3(int n) {
        if( n < 1 ) return 1;
        return n * factorial3(--n);
    }
    public static void main(String[] args) {

        int result = factorial1(5);
        factorial2(5);

        System.out.println("팩토리얼1 결과 : " + result );
        System.out.println("팩토리얼2 결과 : " + res );
        System.out.println("팩토리얼3 결과 : " + factorial3(5) );

    }

}
