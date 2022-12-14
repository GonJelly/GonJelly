package study.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex_DiceTest {

    static int R;           // 주사위를 던질 횟수
    static int totalCount;  // 던져서 나온 주사위의 조합의 수
    static int[] numbers;   // 수의 경우를 저장할 배열
    static boolean[] isSelected; // 중복을 체크하기 위한 배열
    
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("주사위의 조합모드를 선택해주세요.!!");
        System.out.println("1 : 중복 순열");
        System.out.println("2 : 순열");
        System.out.println("3 : 중복조합");
        System.out.println("4 : 조합");
        int mode = Integer.parseInt(reader.readLine());

        System.out.println("주사위를 몇번 던질지 입력해주세요.");
        R = Integer.parseInt(reader.readLine());

        numbers = new int[R];

        // 주사위 던지기
        switch( mode ) {
            case 1:
                dice1(0);
                break;
            case 2:
                isSelected = new boolean[7];
                dice2(0);
                break;
            case 3:
                dice3( 0, 1);
                break;
            case 4:
                dice4( 0, 1);
                break;
        }
        System.out.println("경우의 수 : " + totalCount);

    }

    // 주사위 던지기1
    // 주사위를 3번 던져서 나올 수 있는 수의 조합을 구하시오! ( 중복 허용 )
    public static void dice1(int cnt) {

       // 기저조건 ( 주어진 숫자만큼 주사위를 던졌으면 종료 )
        if( cnt == R ) {
            totalCount++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        // 가능한 모든 경우 시도
        for( int r = 1; r <= 6; r++) {
            // 던진 주사위 수 저장
            numbers[cnt] = r;
            // 다음 주사위 던지기
            dice1(cnt + 1);
        }
    }
    
    // 주사위 던지기2
    // // 주사위를 3번 던져서 나올 수 있는 수의 조합을 구하시오! ( 중복 불가 )
    public static void dice2(int cnt) {

        // 기저조건 ( 주어진 숫자만큼 주사위를 던졌으면 종료 )
        if( cnt == R ) {
            totalCount++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        // 가능한 모든 경우 시도
        for( int r = 1; r <= 6; r++) {
            // 던진 주사위가 나왔는지 아닌지 검사
            if( isSelected[r] ) continue;
            isSelected[r] = true;
            // 던진 주사위 수 저장
            numbers[cnt] = r;
            // 다음 주사위 던지기
            dice2(cnt + 1);
            isSelected[r] = false;
        }
    }
    
    // 주사위 던지기3 : 중복조합
    public static void dice3(int cnt , int start) {

        // 기저조건 ( 주어진 숫자만큼 주사위를 던졌으면 종료 )
        if( cnt == R ) {
            totalCount++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for( int r = start; r <= 6; r++ ) {
            numbers[cnt] = r;
            dice3(cnt + 1, r);
        }
    }
    // 주사위 던지기4 : 조합
    public static void dice4(int cnt, int start) {

        // 기저조건 ( 주어진 숫자만큼 주사위를 던졌으면 종료 )
        if( cnt == R ) {
            totalCount++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for( int r = start; r <= 6; r++ ) {
            // 선택한 주사위 수를 저장
            numbers[cnt] = r;
            // 다음 주사위 선택
            dice4( cnt + 1, r + 1);
        }
    }
}
