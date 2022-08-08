package swea;

import java.io.*;
import java.util.StringTokenizer;

public class D3_9229 {
    // 전형적인 조합문제
    private static int max;
    private static int N;
    private static int M;
    private static int[] gram;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/spotmart_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());                // 테스트 케이스

        for(int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());                   // N : 과자봉지의 갯수
            M = Integer.parseInt(st.nextToken());                   // M : 과자 최대무게
            max = 0;

            gram = new int[N+1];                                    // 각 과자봉지의 무게

            st = new StringTokenizer(in.readLine());                // 과자 무게 할당
            for(int i = 1; i <= N; i++) {
                gram[i] = Integer.parseInt(st.nextToken());
            }

            selected(1,0,0);                            // 최적의 무게 선택하기

            if( max == 0 ) { // 최댓값이 0이라는 것은 최적의 무게가 없다는 것!!
                sb.append("#").append(t).append(" -1").append("\n");
            } else {        // 최댓값을 출력
                sb.append("#").append(t).append(" ").append(max).append("\n");
            }

        }
        System.out.println(sb);
        in.close();
    }

    private static void selected(int idx, int cnt, int sum) {

        if( cnt == 2 && sum <= M) {
            max = Math.max(max,sum);
            return;
        }

        if( cnt == 2 || idx > N ) {
            return;
        }
        // 선택할 경우
        selected(idx + 1,cnt + 1, sum + gram[idx]);
        // 선택 안했을 경우
        selected(idx + 1, cnt, sum);
    }
}
