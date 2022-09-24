package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 스위치 켜기 끄기
* */
public class Main_1244 {

    static int[][] status;                              // 학생의 성별/스위치 번호 저장공간

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 스위치의 길이
        int[] switchBtn = new int[N + 1];               // 스위치 생성
        int students = 0;                               // 학생 수

        /* 스위치가 1부터 N까지 상태를 확인하기 때문에 N + 1로 하였고 */
        /* for문도 1부터 초기값을 할당을 하였습니다. */
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            switchBtn[i] = Integer.parseInt(st.nextToken());    // ex) 0 1 0 1 0 0 0 1
        }
        /* 학생 수 할당 */
        st = new StringTokenizer(br.readLine());
        students = Integer.parseInt(st.nextToken());

        /* 학생이 스위치를 변경해야하는 행동 지정 */
        /* [0] 학생의 성별, [1] 학생이 받은 숫자 ( 1 ~ N ) */
        status = new int[students + 1][2];
        for( int j = 1; j <= students; j++) {
            st = new StringTokenizer(br.readLine());
            status[j][0] = Integer.parseInt(st.nextToken());
            status[j][1] = Integer.parseInt(st.nextToken());
        }

        /* 스위치 변경 start */
        for(int k = 1; k <= students; k++) {
            /* 남학생일 경우 */
            if( status[k][0] == 1) {
                switchBtn = male(switchBtn, status[k][1]); // 0 1 1 1 0 1 0 1
            }
            /* 여학생일 경우 */
            else if( status[k][0] == 2) {
                changeBtn(switchBtn, status[k][1]);
                switchBtn = female(switchBtn, status[k][1] - 1, status[k][1] + 1); // 1 0 1 0 1 1 0 1
            }
        }
        /* 스위치 변경 end */

        // 스위치 상태 출력
        for(int i = 1; i <= N; i++) {
            System.out.print(switchBtn[i] + " ");
            if( i % 20 == 0 ) {
                System.out.println();
            }
        }
    }

    /**
     * 남자가 스위치를 변경하는 방법
     * @param switchBtn
     * @param btn
     * @return
     */
    private static int[] male(int[] switchBtn, int btn) {

        for(int i = btn; i < switchBtn.length; i += btn ) {
            changeBtn(switchBtn, i);
        }
        return switchBtn;
    }

    /**
     * 여자가 스위치를 변경하는 방법 함수
     * @param switchBtn
     * @param btn_w
     * @param btn_e
     * @return
     */
    private static int[] female(int[] switchBtn, int btn_w, int btn_e) {
        // 범위를 벗어나는지 점검
        if( btn_w <= 0 || btn_e >= switchBtn.length ) {
            return switchBtn;
        }

        if( switchBtn[btn_w] == switchBtn[btn_e]) {
            changeBtn(switchBtn, btn_w);
            changeBtn(switchBtn, btn_e);
            return female(switchBtn, btn_w - 1, btn_e + 1);
        } else {
            return switchBtn;
        }
    }

    /**
     * 스위치 버튼 변경 함수
     * @param switchBtn
     * @param btn
     */
    private static void changeBtn(int[] switchBtn, int btn ) {
        if ( switchBtn[btn] == 0 ) {
            switchBtn[btn] = 1;
        } else if (switchBtn[btn] == 1) {
            switchBtn[btn] = 0;
        }
    }
}
