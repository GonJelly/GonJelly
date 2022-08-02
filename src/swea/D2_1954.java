package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D2_1954 {

    public static void main(String[] args) throws IOException {

//        FileInputStream file = new FileInputStream("./study_algorithm/res/ladder_input.txt");
//        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 할당
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {

            int n = Integer.parseInt(br.readLine()); // 배열의 크기
            int count = n;                          // 진행 횟수
            int number = 1;                         // 출력할 숫자
            int swt = 1;                            // 스위치
            int[][] arr = new int[n][n];

            int start = 0;                          // 삽입할 x 좌표
            int end = -1;                           // 삽입할 y 좌표

            while(true) {
                // y축 진행
                for(int i = 0; i < count; i++ ) {
                    end += swt;
                    arr[start][end] = number++;
                }

                count--;                // 진행 횟수 감소
                if( count == 0) break;  // 더이상 진행이 안되면 종료

                // x축 진행
                for(int j = 0; j < count; j++) {
                    start += swt;
                    arr[start][end] = number++;
                }

                swt = -swt;             // 부호전환
            }
            // 출력 start
            System.out.printf("#%d%n",t);
            for(int i = 0; i < n; i++) {
               for(int j = 0; j <n; j++) {
                   System.out.printf("%d ",arr[i][j]);
               }
                System.out.println();
            }
            // 출력 end
        }
    }
}