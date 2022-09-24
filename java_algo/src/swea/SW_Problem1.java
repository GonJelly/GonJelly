package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW_Problem1 {

    static class Gate {

        int number;
        int people;

        public Gate(int number, int people) {
            this.number = number;
            this.people = people;
        }
    }

    private static int place , min;

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/fisher.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for(int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(in.readLine());
            place = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;

            Gate[] gate = new Gate[3];

            for(int i = 0; i < 3; i++ ) {
                st = new StringTokenizer(in.readLine());

                int number = Integer.parseInt(st.nextToken());
                int people = Integer.parseInt(st.nextToken());

                gate[i] = new Gate( number, people);
            }

            boolean[] isUse = new boolean[place];

            open(0,0, 0,gate,isUse);

            sb.append("#").append(t).append(" ").append(min).append("\n");
        }

        System.out.print(sb);
        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000_000_000 + "s");
    }

    private static void open(int cnt, int flag , int sum, Gate[] gate, boolean[] isUse) {

        if( cnt == 3 ) {
            min = Math.min(min, sum);
            return;
        }

        // 게이트의 갯수는 항상 3개
        for(int i = 0; i < 3; i++) {
            if( (flag & 1<<i ) != 0) continue;
//            System.out.println("------------- " + cnt + "번째 --------------");
            int[] seat = new int[place + 1];
            boolean[] tmp = Arrays.copyOf(isUse,place + 1);
            if( gate[i].people % 2 == 0) {
                seat = push(gate[i],tmp);
                int total = sum(seat);
//                System.out.println("왼쪽 우선 : " + Arrays.toString(seat));
                open(cnt + 1, flag | 1<<i, sum + total, gate, tmp);

                tmp = Arrays.copyOf(isUse,place + 1);

                seat = pushRight(gate[i],tmp);
                total = sum(seat);
//                System.out.println("오른쪽 우선 : " + Arrays.toString(seat));
                open(cnt + 1, flag | 1<<i, sum + total, gate, tmp);

            } else {
                seat = push(gate[i], tmp);
//                System.out.println(Arrays.toString(seat));
                int total = sum(seat);
                open(cnt + 1, flag | 1<<i, sum + total, gate , tmp);
            }
        }

    }

    private static int[] push( Gate gate , boolean[] isUse) {

        int[] seat = new int[place + 1];
        int count = 1;
        int dis = 0;
        int num = gate.number;
        while ( gate.people >= count) {
            // 좌
            if ( num - dis > 0 && !isUse[num - dis] ) {
                isUse[num - dis] = true;
                seat[num - dis] = dis + 1;
                count++;
            }

            if( gate.people < count ) break;

            // 우
            if( num + dis <= place && !isUse[num + dis] ) {
                isUse[num + dis] = true;
                seat[num + dis] = dis + 1;
                count++;
            }

            dis++;
        }

        return seat;
    }

    private static int[] pushRight( Gate gate , boolean[] isUse) {

        int[] seat = new int[place + 1];
        int count = 1;
        int dis = 0;
        int num = gate.number;
        while ( gate.people >= count) {

            // 우
            if( num + dis <= place && !isUse[num + dis] ) {
                isUse[num + dis] = true;
                seat[num + dis] = dis + 1;
                count++;
            }

            if( gate.people < count) break;

            // 좌
            if ( num - dis > 0 && !isUse[num - dis] ) {
                isUse[num - dis] = true;
                seat[num - dis] = dis + 1;
                count++;
            }


            dis++;
        }

        return seat;
    }

    private static int sum ( int[] seat) {
        int sum = 0;
        for( int x : seat ) {
            sum += x;
        }
        return sum;
    }
}
