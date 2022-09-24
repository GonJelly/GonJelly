package swea.D3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_6808 {

    private static int win_cnt, lose_cnt; // 규영이가 승리한 횟수, 패배한 횟수
    private static int[] g,y;
    private static boolean[] card;

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./res/swea/cardGame_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
//            win_cnt = 0; lose_cnt = 0; sum_g = 0; sum_y = 0;            // 새로운 게임이 시작할 때마다 초기화
            card = new boolean[19];
            g = new int[card.length/2];                                   // 규영이가 낸 카드 순서
            y = new int[card.length/2];                                   // 인영이 카드
            for(int i = 0; i < card.length/2; i++ ) {                     // 카드번호
                g[i] = Integer.parseInt(st.nextToken());
                card[g[i]] = true;
            }

            for(int i = 0; i < card.length/2; i++) {
                for(int j = 1; j < card.length; j++) {
                    if( !card[j] ) {
                        y[i] = j;
                        card[j] = true;
                        break;
                    }
                }
            }

            System.out.println(Arrays.toString(g));
            System.out.println(Arrays.toString(y));

            game(0,0,0);

            System.out.println(win_cnt + " " + lose_cnt);
        }

    }

    private static void game( int idx ,int sum_g,int sum_y) {

        if( idx == 9 ) {
            if( sum_g > sum_y) {
                win_cnt++;
                return;
            } else if(sum_g < sum_y ) {
                lose_cnt++;
                return;
            }
            return;
        }

        for(int i = idx; i < 9; i++) {
            if( g[idx] > y[i] ) {
                int sum = g[idx] + y[i];
                game(idx + 1,sum_g + sum, sum_y);
            } else if( g[idx] < y[i] ) {
                int sum = g[idx] + y[i];
                game(idx + 1, sum_g, sum_y + sum);
            }
        }
    }
}
