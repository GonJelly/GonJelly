package baekjoon.label5000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5710_for {
    static int[] money = {0,2,3,5,7};
    static int[] range = {0,100,10000,1000000,1000000000};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        while( true ) {

            st = new StringTokenizer(in.readLine());
            // 이웃과 사용량을 합쳤을 때 계산된 요금
            int user1 = Integer.parseInt(st.nextToken());
            // 이웃의 요금과 상근이의 요금의 차이
            int user2 = Integer.parseInt(st.nextToken());
            // 종료구간
            if( user1 == 0 && user2 == 0) break;
            // 총전력 구하기 ( 이웃 + 상근이 )
            int total = useToTotal(user1);
            // 이분탐색 시작!!
            int start = 0;      // 시작부분
            int end = total / 2;// 끝부분

            while( start <= end ) {
                int mid = (start + end) / 2;            // 중앙
                int moneyA = useToMoney(mid);           // 상근이의 요금
                int moneyB = useToMoney(total - mid);   // 이웃의 요금
                int diff = Math.abs(moneyA - moneyB);   // 상근이와 이웃의 요금 차이
                if( user2 < diff ){
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

        int sum = 0; // 사용된 총전력

        for(int i = 1; i < 5; i++) {
            // 1000000 를 넘으면 나머지는 전부 7요금을 적용하기 때문에
            // 끝내야합니다. ( 만약 하지않는다면 오버플로우 발생!!!
            if( i == 4 ) {
                sum += a / money[i];
                break;
            }
            // 이미 차감된 전력은 계산하지 않는다.
            int wh = range[i] - range[i - 1];
            // 차감되는 요금 계산
            int monus = money[i] * wh;
            // 차감되는 요금보다 작다면 종료!!
            if( a - monus <= 0) {
                sum += (a / money[i]);
                break;
            }
            // 요금 차감
            a -= monus;
            // 사용된 전력 추가
            sum += wh;
        }
        return sum;
    }

    private static int useToMoney(int use) {

        int total = 0;  // 요금

        for(int i = 1; i < 5; i++){
            // 1000000 를 넘으면 나머지는 전부 7요금을 적용하기 때문에
            // 끝내야합니다. ( 만약 하지않는다면 오버플로우 발생!!!
            if( i == 4 ) {
                total += use * money[i];
                break;
            }
            // 정산된 전력은 계산하지 않는다.
            int wh = range[i] - range[i-1];
            // 요금 계산하기
            int plus = money[i] * wh;
            // 남은 전력이 차감되는 전력보다 작다면 종료
            if( use - wh <= 0) {
                total += use * money[i];
                break;
            }
            use -= wh;      // 남은 전력
            total += plus;  // 납부 금액
        }

        return total;
    }
}
