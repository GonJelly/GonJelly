package baekjoon.label2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839 {

    private static final int five = 5;
    private static final int three = 3;
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int sugar = Integer.parseInt(in.readLine());
        boxing(sugar);
        System.out.println(min);

    }

    private static void boxing(int sugar) {

        int count = 0;

        while ( sugar > 0 ) {
            if( sugar % five == 0) {
                min = (sugar / five) + count;
                return;
            } else {
                min = ++count;
                sugar -= three;
            }
        }

        if( sugar < 0 ) min = -1;
    }
}
