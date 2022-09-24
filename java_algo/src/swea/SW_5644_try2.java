package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 무선 충전
 * @author 박준영
 */
public class SW_5644_try2 {

    static class Battery implements Comparable<Battery>{

        int x;
        int y;
        int c;
        int p;

        public Battery(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Battery{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", c=").append(c);
            sb.append(", p=").append(p);
            sb.append('}');
            return sb.toString();
        }


        @Override
        public int compareTo(Battery o) {
            if( this.p - o.p > 0) return -1;
            else if( this.p - o.p > 0) return 1;
            else return 0;
        }
    }

    private static int m,n;
    // x , y ==> col , row
    private static int[][] map = new int[11][11];
    private static int[][] direction = {
            {0,0},{-1,0},{0,1},{1,0},{0,-1}
    };
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/BC_input2.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(input.nextToken());    // 테스트 케이스 횟수

        for(int t = 1; t <= T; t++) {

            input = new StringTokenizer(in.readLine());

            m = Integer.parseInt(input.nextToken()); // 명령의 갯수
            n = Integer.parseInt(input.nextToken()); // 충전기의 갯수
            int total = 0;
            String[] commandA = in.readLine().split(" ");   // 사용자A 의 명령들
            String[] commandB = in.readLine().split(" ");   // 사용자B 의 명령들

            List<int[]> userA = new ArrayList<>();      // 명령의 수행했을 때 유저A 좌표 리스트
            List<int[]> userB = new ArrayList<>();      // 명령의 수행했을 때 유저A 좌표 리스트

            userA.add(new int[]{1,1});                  // 유저A초기 좌표 셋팅
            userB.add(new int[]{10,10});                // 유저B초기 좌표 셋팅

            getUserCordinate(commandA, userA);          // 유저A의 좌표를 받아옴
            getUserCordinate(commandB, userB);          // 유저B의 좌표를 받아옴

//            System.out.println(userA.size() +"::"+ userB.size());

            // 좌표 확인
//            userA.forEach(arr -> System.out.print(Arrays.toString(arr)));
//            System.out.println();
//            userB.forEach(arr -> System.out.print(Arrays.toString(arr)));

            List<Battery> battery = new ArrayList<>(); // 배터리의 정보를 보관하는 곳
            for(int i = 1; i <= n; i++) {
                input = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(input.nextToken());    // 열
                int y = Integer.parseInt(input.nextToken());    // 행
                int c = Integer.parseInt(input.nextToken());    // 충전범위
                int p = Integer.parseInt(input.nextToken());    // 충전량
                battery.add(new Battery(x,y,c,p));
            }
            // 배터리 정보 확인
            Collections.sort(battery);      // 충전량에 기준으로 내림차순
            battery.forEach(System.out::println);
            // 명령의 횟수만큼 반복
            for(int i = 0; i <= m; i++) {
                total += charger(userA.get(i), userB.get(i), battery);
//                if( i == 7 ) break;
            }

//            System.out.println(total);
            sb.append("#" + t + " ").append(total + "\n");
        }
        System.out.println(sb);
    }

    private static int charger(int[] userA, int[] userB, List<Battery> battery) {

        int chargerSum = 0;

        List<Battery> valiableA = new ArrayList<>();    // 유저A가 사용가능한 배터리
        List<Battery> valiableB = new ArrayList<>();    // 유저B가 사용가능한 배터리

        System.out.print(Arrays.toString(userA));
        System.out.print(Arrays.toString(userB));
        System.out.println();

        for(int i = 0; i < n; i++) {
            // 각 유저와 배터리와의 거리
            int disA = getDistance(userA,battery.get(i));
            int disB = getDistance(userB,battery.get(i));
            // 충전가능 범위에 있으면 각 유저 보관 장소에 저장
            if( disA <= battery.get(i).c) valiableA.add(battery.get(i));
            if( disB <= battery.get(i).c) valiableB.add(battery.get(i));
        }
        // 갯수 확인
        System.out.println(valiableA + "::" + valiableB);

        // 둘다 충전기를 하나에서 충전할 경우
        if( valiableA.size() == 1 && valiableB.size() == 1) {
            // 같은 배터리인지 확인
            if( valiableA.get(0).equals(valiableB.get(0))) {
                chargerSum += (valiableA.get(0).p / 2) + (valiableB.get(0).p / 2);
            } else {
                chargerSum += (valiableA.get(0).p) + (valiableB.get(0).p);
            }
        }
        // A,B 모두 충전할 충전기가 없을 경우 충전하지 않음
        else if( valiableA.size() == 0 && valiableB.size() == 0) {
            chargerSum += 0;
        }
        // 유저A가 충전할 충전기가 없을 경우
        else if( valiableA.size() == 0 ) {
            // B만 충전
            chargerSum += valiableB.get(0).p;
        }
        // 유저B가 충전할 충전기가 없을 경우
        else if( valiableB.size() == 0 ) {
            // A만 충전
            chargerSum += valiableA.get(0).p;
        }
        // 같은 충전기를 사용하는 경우가 발생
        else if( valiableA.size() > valiableB.size() ) {
            // 먼저 유저A에서 배터리를 파워를 비교
            int max = valiableA.get(0).p;
            // 충전량이 제일 많은 충전기가 같다면 분배했을 때와 비교
            if( valiableA.get(0).equals(valiableB.get(0)) ) {
                if( valiableA.get(1).p <= (max / 2) ) {
                    chargerSum += (valiableA.get(0).p / 2) + (valiableB.get(0).p / 2);
                } else {
                    chargerSum += valiableA.get(1).p + valiableB.get(0).p;
                }
            } else {
                chargerSum += valiableA.get(0).p + valiableB.get(0).p;
            }
        } else if ( valiableA.size() < valiableB.size() ) {
            // 먼저 유저B에서 배터리를 파워를 비교
            int max = valiableB.get(0).p;
            if( valiableA.get(0).equals(valiableB.get(0)) ) {
                if( valiableB.get(1).p <= (max / 2) ) {
                    chargerSum += (valiableA.get(0).p / 2) + (valiableB.get(0).p / 2);
                } else {
                    chargerSum += valiableA.get(0).p + valiableB.get(1).p;
                }
            } else {
                chargerSum += valiableA.get(0).p + valiableB.get(0).p;
            }
        } else {
            // 충전량 비교
            if( valiableA.get(0).equals(valiableB.get(0)) ) {
                int max = valiableA.get(0).p;
                // 두번째로 큰놈 비교
                if( valiableA.get(1).p < valiableB.get(1).p ) {
                    if( max / 2 > valiableB.get(1).p ) {
                        chargerSum += (valiableA.get(0).p / 2) + (valiableB.get(0).p / 2);
                    } else {
                        chargerSum += valiableA.get(0).p + valiableB.get(1).p;
                    }
                } else if( valiableA.get(1).p >= valiableB.get(1).p ) {
                    if( max / 2 > valiableA.get(1).p ) {
                        chargerSum += (valiableA.get(0).p / 2) + (valiableB.get(0).p / 2);
                    } else {
                        chargerSum += valiableA.get(1).p + valiableB.get(0).p;
                    }
                }
            } else {
                chargerSum += valiableA.get(0).p + valiableB.get(0).p;
            }
        }


        System.out.println(chargerSum);
        return chargerSum;
    }


    // 유저의 좌표를 구해오는 메서드
    private static void getUserCordinate(String[] command, List<int[]> user) {
        for(int i = 0; i < m; i++) {
            int cmd = Integer.parseInt(command[i]);
            int nx = user.get(i)[0] + direction[cmd][0];
            int ny = user.get(i)[1] + direction[cmd][1];
            user.add(new int[]{nx,ny});
        }
    }

    // 거리구하는 메서드
    private static int getDistance(int[] user, Battery bc) {
        return Math.abs(bc.x - user[1]) + Math.abs(bc.y - user[0]);
    }
}
