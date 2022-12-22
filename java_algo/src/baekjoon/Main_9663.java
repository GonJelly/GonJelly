package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N-Queen
public class Main_9663 {

    static int N;       // 체스판의 크기를 지정할 변수
    static int count;   // 퀀이 서로 공격하지 않고 지정되는 경우의 수
    static int[] chess; // 체스판의 상태를 저장할 배열 생성

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 체크판의 크기를 지정하기
        N = Integer.parseInt(reader.readLine());

        // 체스판의 크기를 할당
        chess = new int[N];
        n_Queen(0);

        System.out.println(count);
    }

    public static void n_Queen(int cnt) {

        // 퀀이 서로 공격하지 않고 배치되었을 경우 카운팅
        if( cnt == N ) {
            count++;
            return;
        }

        // 퀀을 배치할 열을 지정 ( 행에는 오직 퀀이 하나 밖에 배치할 수 없다 )
        for( int i = 0; i < N; i++ ) {

            boolean isvail = true;

            // 첫행이 아니라면 이전 행에 퀀이 어디에 배치되어 있는지 확인
            if( cnt > 0 ) {
                for( int r = 1; cnt - r >= 0; r++ ) {
                    // 같은 열에 배치되어 있으면 false
                    if( chess[cnt - r] == i ) {
                        isvail = false;
                        break;
                    }
                    // 왼쪽 대각선에 배치되어 있으면 false
                    if( i - r >= 0 && chess[cnt - r] == i - r ) {
                        isvail = false;
                        break;
                    }
                    // 오른쪽 대각선에 배치되어 있으면 false
                    if( i + r < N && chess[cnt - r] == i + r ) {
                        isvail = false;
                        break;
                    }
                }
            }
            // 해당 열에 퀀을 배치할 수 없으면 다음 열자리 확인
            if( !isvail ) continue;
            // 퀀을 배치
            chess[cnt] = i;
            // 다음 행으로 이동해서 퀀을 배치
            n_Queen(cnt + 1);
        }

    }

}
