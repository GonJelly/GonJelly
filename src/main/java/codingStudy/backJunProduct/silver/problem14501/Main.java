package codingStudy.backJunProduct.silver.problem14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 전환) Scanner 대신 BufferReader를 사용하였음
 *
 * 문제점 ) 최대의 이익을 내야한다!!
 * */
public class Main {

    static int total = 0;
    static Profit[] arr_profit;

    public static void main(String[] args) throws IOException {

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(scan.readLine());

        arr_profit = new Profit[N];
        for(int i=0; i < N; i++) {
            String str_profit = scan.readLine();
            Profit profit = new Profit();

            if(str_profit.indexOf(" ") > -1) {

                String[] int_profit = str_profit.split(" ");

                profit.setDate(Integer.parseInt(int_profit[0]));
                profit.setProfit(Integer.parseInt(int_profit[1]));

                // 상담일자가 퇴사일을 넘어가면 상담이 불가하므로 cost는 0 변경
                if(N < i + Integer.parseInt(int_profit[0])){
                    profit.setProfit(0);
                }

                arr_profit[i] = profit;
            } else {
                profit.setDate(1);
                profit.setProfit(0);

                arr_profit[i] = profit;
            }


        }

        // 최대이익을 구하는 구간
        for(int i=0; i < arr_profit.length; i++) {

            // 앞에 상담일자가 1보다 크면 뒤에 상담은 불가하므로 일자가 겹치는 상담일 cost도 0 변경
            if(arr_profit[i].getDate() > 1 && arr_profit[i].getProfit() != 0) {

                // 앞에 cost가 자신보다 높으면 앞에 걸로 수정
                if (arr_profit[i].getProfit() < arr_profit[i + 1].getProfit()){
                    arr_profit[i].setProfit(0);
                    continue;
                }

                int n_date = i + arr_profit[i].getDate();

                if(i < arr_profit.length - 1) {
                    for(int j = i + 1; j <  n_date; j++) {
                        arr_profit[j].setProfit(0);
                    }
                    total += arr_profit[i].getProfit();
                }
            } else {
                total += arr_profit[i].getProfit();
            }

        }

        System.out.println(total);
    }
}

class Profit {

    private int date = 0;
    private int profit = 0;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        if(date >= 1 && date <= 5) {
            this.date = date;
        } else {
            this.date = 0;
        }
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        if(profit >= 1 && profit <= 1000) {
            this.profit = profit;
        } else {
            this.profit = 0;
        }
    }
}
