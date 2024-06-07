package study.step3;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {

    static int[] input;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 집합원소의 수를 입력
        int n = sc.nextInt();
        // 찾아야하는 숫자
        int target = sc.nextInt();
        // 원소를 저장할 배열
        input = new int[n];

        // 원소 할당
        for ( int i = 0; i < n; i++ ) {
            input[i] = sc.nextInt();
        }

        // 집합을 정렬한다.!!
        Arrays.sort(input);

        int start = 0;
        int end = n;
        
        // 시작지점과 끝지점이 반전되지 않았으면 계속 탐색
        while( start < end ) {

            // 중간지점을 찾는다.
            int pivot = (start + end) / 2;

            // 만약 타겟이 키 값보다 크면 시작지점 변경
            if( input[pivot] < target )
                start = pivot + 1;
            // 만약 타겟이 키 값보다 작으면 끝지점 변경
            else if ( input[pivot] > target )
                end = pivot - 1;
            // 중간지점에 값과 타겟값이 같다면 종료
            else {
                System.out.println( target + "을 찾았습니다.");
                break;
            }

        }
    }
}
