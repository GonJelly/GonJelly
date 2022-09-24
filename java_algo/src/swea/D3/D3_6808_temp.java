package swea.D3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_6808_temp {

    private static int win_cnt, lose_cnt; // 규영이가 승리한 횟수, 패배한 횟수
    private static int sum_g, sum_y;
    private static int[] g;

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./res/swea/cardGame_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            win_cnt = 0; lose_cnt = 0; sum_g = 0; sum_y = 0;        // 새로운 게임이 시작할 때마다 초기화
            int totalCard = 18;
            boolean[] card = new boolean[totalCard + 1];            // 사용가능한 카드번호 체크
            g = new int[totalCard/2];                               // 규영이가 낸 카드 순서
            for(int i = 0; i < totalCard/2; i++ ) {                 // 카드번호
                g[i] = Integer.parseInt(st.nextToken());
                card[g[i]] = true;
            }

            game(0,card);

//            System.out.println(Arrays.toString(card));
//            System.out.println(Arrays.toString(g));

            sb.append("#"+t).append(" ").append(win_cnt +" "+lose_cnt).append("\n");

        }

        System.out.println(sb);

    }

    private static void game(int idx, boolean[] card) {
        // 9장의 카드를 모두 비교해서 최종 합산을 비교
        if( idx == card.length/2 ) {
            if (sum_g > sum_y) {
//                System.out.println(sum_g + "  " + sum_y+ " " + "규영" + " 카운트 : " + idx);
                win_cnt++;
                return;
            } else if (sum_g < sum_y) {
//                System.out.println(sum_g + "  " + sum_y+ " " + "인영" + " 카운트 : " + idx);
                lose_cnt++;
                return;
            }
        }
        // 1 ~ 18카드 중에서 규영이가 가지고 있지않은 카드비교
        for(int i = 1; i < card.length; i++) {
            if( !card[i] ) {
                card[i] = true;                     // 카드를 사용했으면  true;
                int temp = 0;
                if( g[idx] > i ) {                  // 규영이가 승리 경우
                    temp = sum_g;
                    sum_g += g[idx] + i;
//                    System.out.print(i);
                    game(idx + 1, card);        // 다음 카드와 비교
                    sum_g = temp;
                } else if( g[idx] < i) {            // 규영이가 패배 경우
                    temp = sum_y;
                    sum_y += g[idx] + i;
//                    System.out.print(i);
                    game(idx + 1, card);        // 다음 카드와 비교
                    sum_y = temp;
                }
                card[i] = false;
            }
        }


    }
}
