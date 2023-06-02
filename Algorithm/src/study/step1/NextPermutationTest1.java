package study.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NextPermutationTest1 {

    static int N;           // 주어지는 숫자 ( 즉, 요소의 갯수 )
    static int totalCount;  // 순열의 갯수
    static int[] numbers;   // 순열을 저장할 배열


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        // 배열의 크기 지정
        numbers = new int[N];
        // 배열에 들어갈 수 입력
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for( int r = 0; r < N; r++ ) {
            numbers[r] = Integer.parseInt(st.nextToken());
        }

        // 전처리 : 배열을 오름차순으로 정렬
        Arrays.sort(numbers);

        do {
            System.out.println(Arrays.toString(numbers));
            totalCount++;
        } while(np());

        System.out.println(totalCount);
    }

    public static boolean np() {

        // 1단계 : 맨 뒤부터 시작해서 꼭대기( 값이 내려가기 시작하는 부분 )을 찾는다.
        int i = N - 1;
        while ( i > 0 && numbers[i - 1] >= numbers[i] ) i--;

        // 맨 앞에 있는 수가 제일 큰 수일 경우!! 종료
        if( i == 0 ) return false;

        // 2단계 : 꼭대기 앞에 있는 자리보다 큰 수를 맨 뒤부터 찾는다.!!
        // ( 결과적으로 꼭대기가 제일 큰 수이기 때문에 꼭대기전까지 큰 수를 못 찾으면 꼭대기와 자리를 바꾸게 된다. )
        int j = N - 1;
        while( numbers[i - 1] >=  numbers[j] ) j--;

        // 3단계 : 꼭대기 앞에 있는 것( i - 1 ) 과 해당 숫자( i - 1 )보다 큰 수( j ) 를 바꾼다.!!
        swap(i - 1, j);

        // 4단계 : 자리를 바꾸었다면 ( i - 1 ) 이전에 있던 숫자들은 오름차순으로 정렬
        int k = N - 1;
        while( i < k ) swap( i++ , k-- );

        return true;
    }

    public static void swap( int i , int j ) {

        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;

    }
}
