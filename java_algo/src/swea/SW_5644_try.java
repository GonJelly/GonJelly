package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW_5644_try {

    static class BC implements Comparable<BC>{
        int x;  // 행의 좌표
        int y;  // 열의 좌표
        int c;  // 충전범위
        int p;  // 충전량

        public BC(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", c=").append(c);
            sb.append(", p=").append(p);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public int compareTo(BC o) {
            if( this.p - o.p > 0) return -1;
            else if( this.p - o.p < 0) return 1;
            else return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BC bc = (BC) o;
            return x == bc.x && y == bc.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static int[][] move = {{0,0},{-1,0},{0,1},{1,0},{0,-1}}; // 정지 | 상 | 우 | 하 | 좌
    private static int M, A,sum;
    private static List<Integer> commandA, commandB;
    private static List<BC> batteryList;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());        // 테스트 케이스의 수

        for(int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            sum = 0;                              // 합산 초기화
            M = Integer.parseInt(st.nextToken()); //    이동시간 (20 <= M <= 100)
            A = Integer.parseInt(st.nextToken()); //    배터리의 수량 (1 <= A <= 8)

            commandA = new ArrayList<>(); // 사용자A의 이동 정보 저장
            commandB = new ArrayList<>(); // 사용자B의 이동 정보 저장

            getCommand(in, commandA); // 이동정보 할당
            getCommand(in, commandB); // 이동정보 할당

            // 이동정보 확인
            System.out.println(commandA);
            System.out.println(commandB);
            // 배터리 정보 저장 start
            batteryList = new ArrayList<>();
            for(int i = 0; i < A; i++) {
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());   // 1 <= C <= 4
                int p = Integer.parseInt(st.nextToken());   // 10 <= p <= 500;
                batteryList.add(new BC(y,x,c,p));
            }
            // 배터리 정보 저장 end
            Collections.sort(batteryList);
            batteryList.forEach(System.out::println);

            charger(0,new BC[M + 1], new BC[M + 1], new int[] {1,1}, 'A');
            sb.append("#" + t + " ").append(sum).append("\n");
        }
        System.out.println(sb);

    }

    private static void charger(int cnt, BC[] listA, BC[] listB, int[] user,char id) {


        if( cnt == M ) {
            if ( id == 'A') {
                charger(0,listA,listB,new int[] {10,10},'B');
                return;
            } else if(id == 'B') {
//                System.out.println("======= A ========");
//                printArr(listA);
//                System.out.println("======= B ========");
//                printArr(listB);
//                System.out.println();
//                System.out.println(listA[19] +"::"+ listB[19]);
                int total = 0;
                for(int i = 0; i < M; i++) {
                    BC tmpA = listA[i];
                    BC tmpB = listB[i];
                    if( tmpA.equals(tmpB) ) {
                        total += (tmpA.p / 2) + (tmpB.p / 2);
                    } else {
                        total += tmpA.p + tmpB.p;
                    }
                }
                System.out.println(total);
                sum = Math.max(sum,total);
            }
            return;
        }

        boolean[] isCheck = new boolean[A];

        for(int i = 0; i < A; i++) {
            BC bc = batteryList.get(i);
            if( bc.c >= getDistance(user,bc) ) {
                if( id == 'A') {
                    int x = user[0] + move[commandA.get(cnt)][0];
                    int y = user[1] + move[commandA.get(cnt)][1];
                    listA[cnt] = bc;
                    charger(cnt + 1, listA, listB, new int[] {x,y},'A');
                } else if ( id == 'B'){
                    int x = user[0] + move[commandB.get(cnt)][0];
                    int y = user[1] + move[commandB.get(cnt)][1];
                    listB[cnt] = bc;
                    charger(cnt + 1, listA, listB, new int[] {x,y},'B');
                }
            }
        }

        if( id == 'A') {
            int x = user[0] + move[commandA.get(cnt)][0];
            int y = user[1] + move[commandA.get(cnt)][1];
            listA[cnt] = new BC(0,0,0,0);
            charger(cnt + 1, listA, listB, new int[] {x,y},'A');
        } else if ( id == 'B'){
            int x = user[0] + move[commandB.get(cnt)][0];
            int y = user[1] + move[commandB.get(cnt)][1];
            listB[cnt] = new BC(0,0,0,0);
            charger(cnt + 1, listA, listB, new int[] {x,y},'B');
        }
    }

    private static <T> void printArr(T[] arr) {
        for(int i = 0; i < M; i++) {
            System.out.println(arr[i]);
        }
    }

    private static int getDistance(int[] user, BC bettery) {
        return Math.abs(user[0] - bettery.x) + Math.abs(user[1] - bettery.y);
    }

    // 사용자 이동정보를 할당하기 위한 함수
    private static void getCommand(BufferedReader in, List<Integer> commandA) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < M; i++) {
            commandA.add(Integer.parseInt(st.nextToken()));
        }
    }
}
