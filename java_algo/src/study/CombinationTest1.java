package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CombinationTest1 {

    // nCr : 1 부터 n까지의 수 중 r개를 순서에 상관없이 뽑는 경우의 수

    static int N;           // 주어지는 숫자 ( 즉, 요소의 갯수 )
    static int R;           // 뽑아야 하는 수
    static int totalCount;  // 조합의 갯수
    static int[] numbers;   // 조합을 저장할 배열

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        R = Integer.parseInt(reader.readLine()); // R의 범위 ( 1 <= R <= N )

        numbers = new int[R];

        // 순열 만들기
        Combi(0,1);

        System.out.println("경우의 수 : " + totalCount);
    }

    public static void Combi(int cnt, int start) {

        // N개를 뽑았다면 종료 ( 기저조건 )
        if( cnt == R ) {
            totalCount++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for( int r = start; r <= N; r++) {
            // 숫자를 선태하였다면 사용중 표시
            numbers[cnt] = r;
            // 다음 숫자를 선택하기
            Combi( cnt + 1, r + 1);
        }
    }
}
