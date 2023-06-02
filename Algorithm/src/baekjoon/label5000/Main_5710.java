package baekjoon.label5000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5710 {
    static int[] money = {0,2,3,5,7};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        while( true ) {

            st = new StringTokenizer(in.readLine());

            int user1 = Integer.parseInt(st.nextToken());
            int user2 = Integer.parseInt(st.nextToken());
            // 종료구간
            if( user1 == 0 && user2 == 0) break;

            int total = useToTotal(user1);

            int start = 0;
            int end = total / 2;

            while( start <= end ) {

                int mid = (start + end) / 2;

                int moneyA = useToMoney(mid);
                int moneyB = useToMoney(total - mid);
                int diff = Math.abs(moneyA - moneyB);
                if( user2 <  diff ){
                    start = mid + 1;
                } else if( user2 > diff ) {
                    end = mid - 1;
                } else {
                    sb.append(moneyA).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }

    private static int useToTotal(int a) {

        if( a <= 200 ) return a / 2;
        else if( a <= 29900) return (a - 200) / 3 + 100;
        else if( a <= 4979900) return (a - 29900) / 5 + 10000;
        else return (a - 4979900) / 7 + 1000000;

    }

    private static int useToMoney(int use) {

        if( use <= 100 ) return use * 2;
        else if( use <= 10000 ) return ( use - 100 ) * 3 + 200;
        else if( use <= 1000000 ) return ( use - 10000 ) * 5 + 29900;
        else return ( use - 1000000 ) * 7 + 4979900;

    }
}
