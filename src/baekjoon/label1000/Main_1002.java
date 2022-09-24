package baekjoon.label1000;

import java.util.*;

/* 터렛문제 */
public class Main_1002 {

    /*
    * 조규현의 좌표 (x1, y1)와 백승환의 좌표 (x2, y2)가 주어지고,
    * 조규현이 계산한 류재명과의 거리 r1과 백승환이 계산한 류재명과의 거리 r2가 주어졌을 때,
    * 류재명이 있을 수 있는 좌표의 수를 출력하는 프로그램을 작성하시오.
    *  x1, y1, r1, x2, y2, r2
    * */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {

            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int r1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            int r2 = in.nextInt();

            double distance = Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2));

            if( x1 == x2 && y1 == y2 && r1 == r2) {
                System.out.println(-1);
            } else if(distance == r1 + r2 || Math.abs(r2 - r1) == distance) {
                System.out.println(1);
            } else if( Math.abs(r2 - r1) > distance || distance == 0 ) {
                System.out.println(0);
            } else if (distance > Math.abs(r2 - r1) && distance < r1 + r2) {
                System.out.println(2);
            }

        }
    }
}
