package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1089_Solution {

    static int N;
    static String[] board;
    static StringBuilder[] boardNumber;
    static double[] sum;
    static int[] count;
    static int recursiveCount;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine());
        board = new String[5];
        boardNumber = new StringBuilder[N];
        sum = new double[N];
        count = new int[N];
        double avg = 0.0;
        boolean isCheck = false;

        long start = 0, end = 0;

        for( int i = 0; i < 5; i++ ) {
            board[i] = input.readLine();
        }

        // 입력값을 1차원 배열로 저장한다.!!!
        for( int r = 0; r < 5; r++ ) {
            for( int c = 0; c < N; c++ ) {
                // 저장공간을 할당하지 않았다면 할당해준다.
                if( boardNumber[c] == null ) boardNumber[c] = new StringBuilder();
                boardNumber[c].append(board[r].substring(4 * c, (4 * c) + 3));
            }
        }
        start = System.currentTimeMillis();
        // 각 자리에서 만들 수 있는 숫자들을 모두 더해서 평균을 구한다.
        for ( int x = 0; x < N; x++ ) {
            setSign(0, boardNumber[x].toString(), x);
//            System.out.println("자릿수 합 : " + sum[x] + " 더한 숫자 수량 : " + count[x]);
            if( count[x] != 0)
                avg += sum[x] / count[x];
            else {
                isCheck = true;
                break;
            }
        }
        end = System.currentTimeMillis();

        // 각 자리수마다 구한 평균값을 더한다.!!
        System.out.println( isCheck ? "-1" : avg );
        System.out.println("재귀 호출 횟수 : " + recursiveCount);
        System.out.println("연산 시간 계산 : " + (end - start));
    }

    private static void setSign(int idx, String sign , int pos) {
        recursiveCount++;
        if( idx == 15 ) {
            int number = setNumber(sign);
            if( number != -1 ) {
                count[pos]++;
                sum[pos] += number * Math.pow(10,N - pos - 1);
            }
            return;
        }

        // 전구가 꺼져있으면 켜서 표시하는 숫자를 확인한다.
        if( idx != 4 && idx != 10  && sign.charAt(idx) == '.') {
            StringBuilder subSign = new StringBuilder();

            if ( idx == 0 )
                subSign.append("#" + sign.substring( idx + 1 ));
            else if( idx < sign.length() )
                subSign.append(sign.substring(0,idx) + "#" + sign.substring(idx + 1));
            else
                subSign.append(sign.substring(0,idx - 1) + "#");

            setSign(idx + 1, subSign.toString(), pos);
        }
        // 전구가 켜져있으면 다음 전구를 확인
        setSign(idx + 1, sign, pos);

    }

    private static int setNumber( String sign ) {
        switch ( sign ) {
            case "####.##.##.####":
                return 0;
            case "..#..#..#..#..#":
                return 1;
            case "###..#####..###":
                return 2;
            case "###..####..####":
                return 3;
            case "#.##.####..#..#":
                return 4;
            case "####..###..####":
                return 5;
            case "####..####.####":
                return 6;
            case "###..#..#..#..#":
                return 7;
            case "####.#####.####":
                return 8;
            case "####.####..####":
                return 9;
            default:
                return -1;
        }
    }


}
