package study.step1;

import java.util.Scanner;

public class SubsetBinaryCounting {

    static int n;           // 원소의 갯수
    static int[] arr;       // 원소를 저장할 배열


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        for( int r = 0; r < n; r++ ) {
            arr[r] = sc.nextInt();
        }

        gernerateSubset();

    }

    public static void gernerateSubset() {

        // flag 시작
        for( int i = 0; i < ( 1 << n ); i++ ) {
            //  배열에 해당하는 자리에 원소가 선택되어 있는지 비교
            for ( int j = 0;  j < n; j++ ) {
                // and 연산을 통해서 해당 자리에 숫자가 포함되어 있으면 출력
                if( (i & ( 1<<j )) != 0 ) System.out.print(arr[j] + " ");
            }
            System.out.println();
        }

    }
}
