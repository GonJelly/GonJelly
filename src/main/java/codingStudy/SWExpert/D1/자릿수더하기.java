package codingStudy.SWExpert.D1;

import java.util.Scanner;

public class 자릿수더하기 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        int result = 0;
        String t = Integer.toString(T);
        char[] t_ch = t.toCharArray();

        for(int i = 0; i < t_ch.length; i++){
            result += Integer.parseInt(String.valueOf(t_ch[i]));
        }

        System.out.print(result);
    }
}
