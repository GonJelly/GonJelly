package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 회의실 배정 문제
public class Main_1931 {

    static int n;           // 회의 개수
    static int[][] reference; // 회의의 시작시간 및 종료시간 저장할 배열


    public static void main(String[] args) throws IOException {

        // 입력받기
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        // 총 배정가능한 회의 수
        int cnt = 1;

        // 회의의 개수 정해주기
        n = Integer.parseInt(st.nextToken());
        reference = new int[n][2];              // [-][0] : 시작 [-][1] : 종료
        // 회의 시작시간 및 종료시간 입력
        for( int i = 0; i < n; i++ ) {
            st = new StringTokenizer(input.readLine());
            reference[i][0] = Integer.parseInt(st.nextToken());
            reference[i][1] = Integer.parseInt(st.nextToken());
        }

        // 빨리 끝나는 회의부터 정렬하기
        // 종료 시간이 같다면 시작시간이 빠른순으로 정렬하기
        Arrays.sort(reference,new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if( o1[1] - o2[1] > 0 ) return 1;
                else if( o1[1] - o2[1] < 0 ) return -1;
                else {
                    return o1[0] - o2[0];
                }
            }
        });

        // 종료 시간
        int fin = reference[0][1];

        // 배정가능한 회의 수 확인
        for( int r = 1; r < n; r++ ) {
            // 이전 회의의 종료시간과 같거나 크면 카운트
            if( reference[r][0] >= fin ) {
                cnt++;                  // 배정가능한 회의 카운트
                fin = reference[r][1];  // 종료 시간 최신화
            }
        }

        // 배정한 회의 출력하기
        System.out.println(cnt);
    }
}
