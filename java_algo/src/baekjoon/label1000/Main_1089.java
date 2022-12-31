package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1089 {

    private static final int FLOOR = 5;
    private static int n , count;
    private static long total;
    private static char[][] light;
    private static int[][] board;
    private static int recursiveCount;

    public static void main(String[] args) throws IOException {

        StringBuffer output = new StringBuffer();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        // 안내판에 나타날 번호들 요소들을 저장
        board = new int[n][10];
        // 안내판의 전구의 상태를 저장할 배열
        light = new char[FLOOR][4*n - 1];
        long start = 0, end = 0;

        int lightOn = 0;
        // 전구의 상태를 할당
        for( int i = 0; i < FLOOR; i++ ) {
            String status = reader.readLine();
            for( int j = 0; j < status.length() ; j++ ) {
                light[i][j] = status.charAt(j);
                if( light[i][j] == '#' ) {
                    lightOn++;
                }
            }
        }
        start = System.currentTimeMillis();
        // 만약 0 ~ 10^n - 1 까지 전부 나타낼 수 있다면 전부 더한다.
        if( lightOn == 0 ) {
            int num = (int) Math.pow(10,n) - 1;
            double avg = (num * (num + 1) / 2) / Math.pow(10,n);
            output.append(avg);
        } else {
            // 주어진 전구의 상태를 확인
            setLight(0,0);
            // 조합가능한 숫자들 총합 구하기
            totalNumber(0,0);
            output.append(total != 0 ? (double) total / count : -1 );
        }
        end = System.currentTimeMillis();

        System.out.println(output);
        System.out.println("재귀 호출 횟수 : " + recursiveCount);
        System.out.println("연산 시간 시작 : " + (start));
        System.out.println("연산 시간 종료 : " + (end));

    }

    private static void totalNumber(int idx, int sum) {

        if( idx == n ) {
            System.out.println(sum);
            total += sum;
            count++;
            return;
        }

        int checksum = 0; // 해당 자릿수가 숫자를 표현할 수 있는지 검사

        // 해당 자릿수를 선택했을 경우
        for( int i = 0; i < 10; i++) {
//            System.out.println((int) Math.pow(10,n - idx - 1));
            if( board[idx][i] > 0 ) {
                checksum++;
                totalNumber(idx + 1, sum + (i * (int) Math.pow(10, n - idx - 1)));
            }
        }

        if( checksum == 0 ) {
            totalNumber(idx + 1, 0);
        }

    }

    private static void setLight(int start, int pos) {

        // 전부 확인했으면 종료한다.
        if( pos == n ) {
            return;
        }
        // 전구의 상태를 확인할 배열
        boolean[] isLight = new boolean[16];
        // 이차원으로 표시된 전구의 상태를 1차원으로 변환
        for( int r = 0; r < 5; r++ ) {
            for( int c = start; c < start + 3; c++ ) {
                // 해당 좌표에 있는 좌표는 켜져있으면 안된다.!!
                if( (r == 1 | r == 3) && c == start + 1 ) continue;

                // 전구가 켜져있으면 true 표시
                if ( light[r][c] == '#' ) {
                    int row = 3 * r + c + 1 - start;
                    isLight[row] = true;
                }
            }
        }

        // 해당 배열을 이용해서 만들 수 있는 숫자를 구한다.
        setNumber(1, isLight, pos);
        setLight(start + 4, pos + 1);

    }

    private static void setNumber(int idx, boolean[] isLight, int pos) {
        recursiveCount++; // 재귀호출을 몇번이나하는 지 확인하기 위해서
        if( idx == isLight.length ) {
            int result = searchNumber(isLight);
//            System.out.println(result);
            if( result != -1 && board[pos][result] == 0) {
                board[pos][result]++;
            }
            return;
        }

        // 전구가 꺼져있으면 해당 전구를 켜서 상태를 확인 ( 단, index 5, 11은 제외
        if ( idx == 5 || idx == 11 ){
            setNumber(idx + 1, isLight, pos);
            return;
        }

        if( !isLight[idx] ) {
            // 전구가 켜신 상태로 확인
            isLight[idx] = true;
            int result = searchNumber(isLight);

            // 숫자가 유효하다면 추가
            if (result != -1) {
                board[pos][result]++;
                setNumber(idx + 1, isLight, pos);
            } else {
                setNumber(idx + 1, isLight, pos);
            }
//
            // 전구가 꺼진 상태로 번호 확인
            isLight[idx] = false;
            result = searchNumber(isLight);
            // 숫자가 유효하다면 추가
            if (result != -1) {
                board[pos][result]++;
                setNumber(idx + 1, isLight, pos);
            } else {
                setNumber(idx + 1, isLight, pos);
            }
        }

        // 이미 켜져있던 전구라서 다음 전구로 이동
        setNumber( idx + 1, isLight , pos);
    }
    
    private static int searchNumber(boolean[] parts) {

        int number = 0;
        for( int i = 1; i < parts.length; i++ ) {
            if( parts[i] == true) {
                number |= 1<<i;
            }
        }
        return getNumberByFlag(number);
    }

    private static int getNumberByFlag(int number) {
        switch ( number ) {
            case 63198:
                return 0;
            case 37448:
                return 1;
            case 59342:
                return 2;
            case 62414:
                return 3;
            case 37850:
                return 4;
            case 62366:
                return 5;
            case 63390:
                return 6;
            case 37454:
                return 7;
            case 63454:
                return 8;
            case 62430:
                return 9;
            default:
                return -1;
        }
    }


}
