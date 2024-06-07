package study.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PermutationBitMaskTest1 {

    // nPn : 1 부터 n까지의 수 중 n개를 뽑아서 순서적으로 나열하는 경우!!
    // nPr : 1 부터 n까지의 수 중 r개를 뽑아서 순서적으로 나열하는 경우
    static int N;           // 주어지는 숫자 ( 즉, 요소의 갯수 )
    static int R;           // 뽑아야 하는 수
    static int totalCount;  // 순열의 갯수
    static int[] numbers;   // 순열을 저장할 배열


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        R = Integer.parseInt(reader.readLine()); // R의 범위 ( 1 <= R <= N )

        numbers = new int[R];

        // 순열 만들기
        permutation(0, 0);

        System.out.println(totalCount);
    }

    public static void permutation(int cnt, int flag) {

        // N개를 뽑았다면 종료 ( 기저조건 )
        if( cnt == R ) {
            totalCount++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for( int r = 1; r <= N; r++) {
            // 선택한 숫자를 사용 중이라면 다음 숫자 사용
            if( (flag & ( 1<<r )) != 0 ) continue;
            // 선택한 숫자를 저장
            numbers[cnt] = r;
            // 다음 숫자를 선택하기
            permutation( cnt + 1 , flag | 1<<r);
        }
    }
}
