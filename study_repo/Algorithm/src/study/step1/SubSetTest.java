package study.step1;

import java.util.Arrays;
import java.util.Scanner;

public class SubSetTest {

    static int N; // 집합의 원소의 갯수
    static int totalCount; // 부분집합의 총 갯수를 카운트할 변수
    static int[] input; // 집합의 원소를 저장할 배열
    static boolean[] isSelected; // 원소를 선택한지 검사하는 배열

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        input = new int[N];
        isSelected = new boolean[N];

        for( int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }
        // 부분집합 구하기
        subset(0);
        // 최종 출력
        System.out.println("부분집합의 수 : " + totalCount );
    }

    public static void subset(int idx) {

        if( idx == N ) {
            totalCount++;
            for( int i = 0; i < N; i++ ) {
                if( isSelected[i] ) System.out.printf("%d ",input[i]);
            }
            System.out.println();
            return;
        }

        // 원소를 선택
        isSelected[idx] = true;
        subset( idx + 1 );

        // 원소를 선택하지 않는 경우
        isSelected[idx] = false;
        subset( idx + 1 );
    }
}
