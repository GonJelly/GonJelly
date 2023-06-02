package study.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PermutationTest1 {

    // nPn : 1 부터 n까지의 수 중 n개를 뽑아서 순서적으로 나열하는 경우!!
    // nPr : 1 부터 n까지의 수 중 r개를 뽑아서 순서적으로 나열하는 경우
    static int N;           // 주어지는 숫자 ( 즉, 요소의 갯수 )
    static int R;           // 뽑아야 하는 수
    static int totalCount;  // 순열의 갯수
    static int[] numbers;   // 순열을 저장할 배열
    static boolean[] isSelected;    // 선택한 숫자를 확인할 배열


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        R = Integer.parseInt(reader.readLine()); // R의 범위 ( 1 <= R <= N )

        numbers = new int[R];
        isSelected = new boolean[N + 1];

        // 순열 만들기
        permutation(0);

        System.out.println(totalCount);
    }

    public static void permutation(int cnt) {

        // N개를 뽑았다면 종료 ( 기저조건 )
        if( cnt == R ) {
            totalCount++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for( int r = 1; r <= N; r++) {
            // 선택한 숫자를 사용 중이라면 다음 숫자 사용
            if( isSelected[r] ) continue;
            // 숫자를 선태하였다면 사용중 표시
            isSelected[r] = true;
            // 선택한 숫자를 저장
            numbers[cnt] = r;
            // 다음 숫자를 선택하기
            permutation( cnt + 1 );
            // 선택한 숫자를 재사용할 수 있도록 사용중 표시 제거
            isSelected[r] = false;
        }
    }
}
