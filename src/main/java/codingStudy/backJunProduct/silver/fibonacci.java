package codingStudy.backJunProduct.silver;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * 미완료 문제 1
 * 필요한 지식 사항
 * 메모이제이션(memoization), 재귀호출 개념, 피보나치 수열
 * 2022년 04월 06일 일시 중시
 * */
public class fibonacci {

    // count[0]은 0이 호출된 횟수
    // count[1]은 1이 호출된 횟수
    static int[] count = {0,0};
//    static Map<Integer, Object> fib = new HashMap<Integer,Object>();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // 반복 횟수 입력
        int n = scan.nextInt();
        int[] fibCount = new int[n];

        // 경우의 수 입력하는 부분
        for(int i = 0; i < fibCount.length; i++){
            fibCount[i] = scan.nextInt();
        }

        // 경우의 수를 대입하여 피보나치 수열 기능하기
        for(int j = 0; j < fibCount.length; j++){

            inn_fibonacci(fibCount[j]);

            // 결과 출력
            System.out.println(count[0] + " " + count[1]);

            // 횟수 초기화
            count = new int[]{0,0};
        }
    }

    // 피보나치 수열 함수
    public static int inn_fibonacci(int a){

        if(a <= 0) {
            count[0]++;
            return 0;
        }
        else if(a == 1) {
            count[1]++;
            return 1;
        }
/**
        // 메모제이션 *** 반복적 작업을 할 때 시간을 줄여주는 기술
        if(fib.containsKey(a)){
            return count[1];
        } else if(!fib.containsKey(a)) {
            fib.put(a, count);
        }
*/

        return inn_fibonacci(a-2) + inn_fibonacci(a-1);
    }
}