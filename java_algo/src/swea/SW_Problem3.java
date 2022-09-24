package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SW_Problem3 {

    private static int n , min;
    private static int[] descending, ascending;

    public static void main(String[] args) throws IOException {


        long start = System.nanoTime();
        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/suffle_o.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        for(int t = 1; t <= T; t++) {

            n = Integer.parseInt(in.readLine());    // 카드의 수
            min = Integer.MAX_VALUE;
            int[] card = new int[n];
            ascending = new int[n];

            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int x = 0; x < n; x++) {
                card[x] = Integer.parseInt(st.nextToken());
            }

//            System.out.println(Arrays.toString(card));

            descending = Arrays.copyOf(card,n);
            // 올림차순 정렬 정답
            Arrays.sort(descending);
            // 내림차순 정렬 정답
            for(int x = 0; x < n; x++) {
                ascending[n - 1 - x] = descending[x];
            }

            // 셔플 시작
            shuffle(0,n/2, card);

            if( min == Integer.MAX_VALUE) {
                sb.append("#").append(t).append(" ").append(-1).append("\n");
            } else {
                sb.append("#").append(t).append(" ").append(min).append("\n");
            }
        }

        System.out.print(sb);
        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000_000_000 + "s");
        in.close();
    }

    private static void shuffle(int cnt, int half, int[] card) {
        // 5회를 초과하면 종료
        if( cnt > 5) {
            return;
        }

        // 최소 셔플횟수보다 크면 종료
        if( min <= cnt ) return;

        // 더미 카드
        int[] tmp = Arrays.copyOf(card,n);

        for (int x = 0, cycle = 0; x < n; x++) {
            int i = Math.abs(half - x); // 시작위치
            // 시작 위치와 회전 수 설정
            for (int c = 0; c < cycle; c++, i += 2) {
                if( i + 1 >= n) break;
                int temp = tmp[i];
                tmp[i] = tmp[i + 1];
                tmp[i + 1] = temp;
            }
//            System.out.print("x ==> " + x);
//            System.out.println(Arrays.toString(tmp));

            if( checkAsc(card) || checkDsc(card) ) {
                min = Math.min(min,cnt);
                return;
            }

            shuffle(cnt + 1, half, tmp);
            if (x < half) cycle++;
            else cycle--;
        }
    }

    private static boolean checkAsc(int[] card) {
        for(int x = 0; x < n; x++) {
            if( ascending[x] != card[x] ) return false;
        }
        return true;
    }

    private static boolean checkDsc(int[] card) {
        for(int x = 0; x < n; x++) {
            if ( descending[x] != card[x] ) return false;
        }
        return true;
    }
}
