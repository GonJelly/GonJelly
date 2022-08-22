package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2174_tmp {

    static class Robot {

        int x;
        int y;
        int z;

        public Robot(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if( this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return x == robot.x && y == robot.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static int A, B;
    private static int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}}; // 상좌하우

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for ( int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            boolean valiable = true;                // 계속 명령을 수행해도 되는지 확인판단
            A = Integer.parseInt(st.nextToken());   // 땅(지도)의 가로
            B = Integer.parseInt(st.nextToken());   // 땅(지도)의 세로

            HashMap<Integer,Robot> robots = new HashMap<>();

            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 로봇의 수 시간:1
            int M = Integer.parseInt(st.nextToken());   // 명령의 수 시간:1

            for (int a = 1; a <= N; a++) {
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());   // 로봇의 x축 위치
                int y = Integer.parseInt(st.nextToken());   // 로봇의 y축 위치
                int z = getPostion(st.nextToken());         // 가리키고 있는 방향
                robots.put(a,new Robot(x,y,z));               // 로봇 위치 방향
//            System.out.println(Arrays.toString(robot.get(a)));
            }

            // 명령어 입력
            List<String[]> command = new ArrayList<>();
            for (int b = 0; b < M; b++) {
                st = new StringTokenizer(in.readLine());
                command.add(new String[]{st.nextToken(), st.nextToken(), st.nextToken()});
//            System.out.println(Arrays.toString(command.get(b)));
            }

//        printMap();

            // 명령수행
            for (int a = 0; a < M; a++) {
                valiable = getCommand(0, command.get(a), robots);
                if (!valiable) {
                    break;
                }
            }
            if (valiable) {
                System.out.println("OK");
            }
        }
    }
    // 입력된 방향을 숫자로 반환
    private static int getPostion(String s) {
        switch ( s ) {
            case "N": return 0;
            case "E": return 1;
            case "S": return 2;
            case "W": return 3;
            default: return -1;
        }
    }

    private static boolean getCommand(int cnt, String[] command, HashMap<Integer,Robot> robots) {

        int robot_NM = Integer.parseInt(command[0]);
        if( cnt != 0 ) {
            // 벽에 붙이쳐서 명령 종료!!
            if (robots.get(robot_NM).y <= 0 || robots.get(robot_NM).y > B ||
                    robots.get(robot_NM).x <= 0 || robots.get(robot_NM).x > A) {
                System.out.println("Robot " + robot_NM + " crashes into the wall");
                return false;
            }

            // 로봇과 충돌하여 명령 종료!!
            int crash_NM = isCheck( robot_NM , robots ); // 충돌하지 않으면 -1
            if( crash_NM != -1 ) {
                System.out.println("Robot " + robot_NM + " crashes into robot " + crash_NM);
                return false;
            }

        }
        // 명령을 모두 수행하면 종료
        if( cnt == Integer.parseInt(command[2]) ) {
            return true;
        }

        switch ( command[1] ) {
            case "L":   // 왼쪽으로 90도 회전
                robots.get(robot_NM).z += 3;
                if( robots.get(robot_NM).z >= 4 ) robots.get(robot_NM).z %= 4;
                return getCommand(cnt + 1, command, robots);
            case "R":  // 오른쪽으로 90도 회전
                robots.get(robot_NM).z += 1;
                if( robots.get(robot_NM).z >= 4 ) robots.get(robot_NM).z %= 4;
                return getCommand(cnt + 1, command, robots);
            case "F":  // 가리키고 있는 방향 앞으로 한칸 전진
                int way = robots.get(robot_NM).z;
                robots.get(robot_NM).x += pos[way][1];
                robots.get(robot_NM).y += pos[way][0];
                return getCommand(cnt + 1, command, robots);
        }

        return true;
    }
    // 같은 로봇이 있는지 확인
    private static int isCheck(int key, HashMap<Integer,Robot> robots) {
        int n = robots.size();
        Robot robot = robots.get(key);
        for(int i = 1; i <= n; i++) {
            if( key == i) continue;
            if( robot.equals(robots.get(i)) ) return i;
        }
        return -1;
    }

}
