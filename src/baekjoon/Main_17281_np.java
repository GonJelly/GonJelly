package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17281_np {

    static class Player {

        int number;
        List<Integer> plays = null;

        public Player(int number) {
            this.number = number;
            this.plays = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Player{" +
                    "number=" + number +
                    ", plays=" + plays +
                    '}';
        }
    }
    static int n,max; // 이닝 횟수

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        FileInputStream file = new FileInputStream("./res/baekjoon/baseball.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        max = 0;

        // 리스트를 이용한 방법
        List<Player> player = new ArrayList<>();   // 선수들이 해당 이닝에서 수행하는 플레이
        for(int i = 0; i <= 9; i++) {
            player.add(new Player(i));
        }

        for( int x = 1; x <= n; x++) {
            st = new StringTokenizer(in.readLine());
            for( int y = 1; y <= 9; y++) {
                int run = Integer.parseInt(st.nextToken());
                player.get(y).plays.add(run);
            }
        }

        player.remove(0);

        do{
            if( player.get(3).number == 1) {
                max = Math.max(max,game(player));
            }
        } while(np(player));

        System.out.println(max);
        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000 + "ms");
        in.close();
    }

    private static int game(List<Player> player) {

        int score = 0;  //  총점
        int turn = 0;   //  타자 순서

        // 이닝 순선
        for(int y = 0; y < n; y++) {
            int[] pos = new int[5]; // 0:타자 1:1루 2:2루 3:3루 4:홈
            int out = 0;    // 이닝이 시작할 때 초기화
            // 3진 아웃이 아니라면 계속
            while ( out != 3) {
                pos[0] = player.get(turn).number;   // 타자
                // 아웃
                if( player.get(turn).plays.get(y) == 0) {
                    pos[0] = 0; // 타자 아웃
                    out++;
                }
                // 1루 전진
                else if ( player.get(turn).plays.get(y) == 1 ) {
                    score += getScore(1,pos);
                }
                // 2루 전진
                else if ( player.get(turn).plays.get(y) == 2 ) {
                    score += getScore(2,pos);
                }
                // 3루 전진
                else if ( player.get(turn).plays.get(y) == 3 ) {
                    score += getScore(3,pos);
                }
                // 홈런
                else if ( player.get(turn).plays.get(y) == 4 ) {
                    score += getScore(4,pos);
                }
                // 다음 순서
                turn++;
                if( turn >= 9) turn = 0;
            }
        }

        return score;
    }

    private static int getScore(int x, int[] pos) {
        int score = 0;

        for( int i = 0; i < x; i++) {
            // 1루씩 전진
            for(int r = 4; r > 0; r--) {
                pos[r] = pos[r - 1];
            }
            if( pos[4] != 0) {
                score++;
            }
            pos[0] = 0;
        }

        return score;
    }
    private static boolean np(List<Player> player) {

        int  i = 8;
        while ( i > 0 &&  player.get(i - 1).number >= player.get(i).number ) --i;

        if( i == 0 ) return false;

        int j = 8;
        while( player.get(i-1).number >= player.get(j).number ) --j;

        swap(player, i - 1, j);

        int k = 8;
        while( i < k) swap(player, i++, k--);

        return true;
    }

    private static void swap(List<Player> player, int i , int j) {
        Player tmp = player.get(i);
        player.set(i,player.get(j));
        player.set(j,tmp);
    }
}
