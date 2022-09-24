package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 무선 충전
 * @author 박준영
 */
public class SW_5644_try3 {

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

        List<Battery> valiableA = new ArrayList<>();    // 유저A가 사용가능한 배터리
        List<Battery> valiableB = new ArrayList<>();    // 유저B가 사용가능한 배터리

//        System.out.print(Arrays.toString(userA));
//        System.out.print(Arrays.toString(userB));
//        System.out.println();

        for(int i = 0; i < n; i++) {
            // 각 유저와 배터리와의 거리
            int disA = getDistance(userA,battery.get(i));
            int disB = getDistance(userB,battery.get(i));
            // 충전가능 범위에 있으면 각 유저 보관 장소에 저장
            if( disA <= battery.get(i).c) valiableA.add(battery.get(i));
            if( disB <= battery.get(i).c) valiableB.add(battery.get(i));
        }
        // 갯수 확인
//        System.out.println(valiableA + "::" + valiableB);

        int AmaxPower = 0, BmaxPower = 0;
        if(valiableB.size() == 0) {         // A가 충전 가능한 배터리가 없는 경우
            for(int a = 0; a < valiableA.size(); ++a) {
                AmaxPower = Math.max(AmaxPower, valiableA.get(a).p);        // A 배터리 목록에서 충전 가능한 배터리 찾음
                // sorting 되어있기 때문에
                // ** AmaxPower = valiableA.get(0).p 해도 될거 같음
            }
        } else if(valiableA.size() == 0) {   // B가 충전  가능한 배터리가 없는 경우 동일하게 처리
            for(int b = 0; b < valiableB.size(); ++b) {
                BmaxPower = Math.max(BmaxPower, valiableB.get(b).p);
                // 동일하게 제일 처음 꺼 가져와도 될 것 같음
            }
        } else {        // A, B 모두 충전 가능한 배터리가 있는 경우
            for(int a = 0; a < valiableA.size(); ++a) {
                for(int b = 0; b < valiableB.size(); ++b) {
                    if( valiableA.get(a).equals(valiableB.get(b))){     // A와 B 가 동일하다면
                        AmaxPower = Math.max(AmaxPower, valiableA.get(a).p);    // A에만 값을 넣음 // 어차피 return으로 A + B의 결과를 넣을 거기 때문에
                        // A에만 값을 넣을 경우 A + 0 으로 상관없음
                    } else {    // A B 동일하지 않다면
                        AmaxPower = Math.max(AmaxPower, valiableA.get(a).p + valiableB.get(b).p);      // A 와 B가 동일 했을 경우
                        // AmaxPower에 저장되어 있기 때문에
                        // AmaxPower와 A + B 를 비교해서 AmaxPower에 저장
                        // 반복문이 끝날때 까지 비교 해야 함 => 모든 배터리 경우의 수에 대해서 비교 해야 함
                    }
                }
            }
        }

        return AmaxPower + BmaxPower;
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
