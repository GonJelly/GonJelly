package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_4013 {

    private static int[] turn;
    private static LinkedList<Integer>[] gears;

    public static void main(String[] args) throws IOException {

//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/cycle1.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine()); // 테스트 케이스 횟수
        // start
        for( int t = 1; t <= T; t++) {

            int cnt = Integer.parseInt(in.readLine());  // 톱니바퀴를 돌리는 경우의 수
            StringTokenizer st;
            int total = 0;                              // 최종 빨간색 화살표에 찍힌 점수 합산 저장
            gears = new LinkedList[5];                  // 톱니바퀴의 바퀴정보 저장
            // 톱니 바퀴 선언
            for(int i = 0; i <= 4; i++) {
                gears[i] = new LinkedList<>();
            }
            // 톱니 할당
            for(int r = 1; r <= 4; r++) {
                st = new StringTokenizer(in.readLine());
                for(int c = 1; c <= 8; c++) {
                    gears[r].add(Integer.parseInt(st.nextToken()));
                }
//                System.out.println(gears[r]);
            }
            // 톱니바퀴를 회전가능 여부 판단 0:정지 1:시계방향 -1:반시계방향
            turn = new int[5]; // 회전방향 제시 0이면 노회전

            for(int i = 0; i < cnt; i++) {
                st = new StringTokenizer(in.readLine());

                int idx = Integer.parseInt(st.nextToken()); // 회전 시킬 톱니의 번호
                int dir = Integer.parseInt(st.nextToken()); // 회전 시킬 톱니의 방향 및 횟수 ( 음수 : 반시계 양수 : 시계 );

//                System.out.printf("%d <--> %d",idx,dir);
                // 재귀를 돌면서 톱니를 돌린다.
                cycle(0,idx,dir);
            }

            for(int i = 1; i <= 4; i++)
                if( gears[i].peek() == 1 ) {
                    total += Math.pow(2,i - 1);
                }

            sb.append("#").append(t).append(" ").append(total).append("\n");
        }

        System.out.println(sb);
    }

    private static void cycle(int count, int idx, int dir) {

        if( count == Math.abs(dir) ) return;

        // 반시계방향
        if( dir < 0) {
            turn[idx] = -1;
        }
        // 시계방향
        else if( dir > 0) {
            turn[idx] = 1;
        }

        // 좌측 검사
        int left = idx;
        int right = idx;

        while ( left > 1) {

            if( turn[left] < 0) {
                if( gears[left].get(6) != gears[left - 1].get(2) ) {
                    turn[left - 1] = 1;
                } else {
                    break;
                }
            } else if ( turn[left] > 0 ) {
                if( gears[left].get(6) != gears[left - 1].get(2) ) {
                    turn[left - 1] = -1;
                } else {
                    break;
                }
            }
            left--;
        }
        // 우측 검사
        while ( right < 4) {
            if( turn[right] < 0) {
                // 좌측 검사
                if( gears[right].get(2) != gears[right + 1].get(6) ) {
                    turn[right + 1] = 1;
                } else {
                    break;
                }

            } else if ( turn[right] > 0 ) {
                // 좌측 검사
                if( gears[right].get(2) != gears[right + 1].get(6) ) {
                    turn[right + 1] = -1;
                } else {
                    break;
                }
            }

            right++;
        }

//        System.out.println(Arrays.toString(turn));

        for(int i = 1; i <= 4; i++) {
            if( turn[i] != 0) {
                // 시계방향으로 회전
                if( turn[i] == 1 ) {
                    // 마지막을 첫번째로
                    int rear = gears[i].pollLast();
                    gears[i].addFirst(rear);
                }
                // 반시계방향으로 회전
                else if( turn[i] == -1) {
                    // 첫번째를 마지막으로
                    int first = gears[i].pollFirst();
                    gears[i].addLast(first);
                }
            }
        }
        // 확인용
//        for(LinkedList<Integer> tmp : gears) {
//            System.out.println(tmp);
//        }

        Arrays.fill(turn,0);

        cycle(count + 1, idx, dir);
    }
}
