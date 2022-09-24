package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683 {
    static class CCTV {

        int kind;
        int[] pos;
        boolean up;
        boolean down;
        boolean left;
        boolean right;


        public CCTV(int kind, int[] pos) {
            this.kind = kind;
            this.pos = pos;

            switch ( kind ) {
                case 1:
                    up = true;
                    break;
                case 2:
                    left = true;
                    right = true;
                    break;
                case 3:
                    up = true;
                    right = true;
                    break;
                case 4:
                    left = true;
                    up = true;
                    right = true;
                    break;
                case 5:
                    left = true;
                    up = true;
                    right = true;
                    down = true;
                    break;
            }
        }

        public boolean[] getmove() {
            boolean[] valiable = new boolean[4];
            valiable[0] = up;
            valiable[1] = down;
            valiable[2] = right;
            valiable[3] = left;
            return valiable;
        }

        @Override
        public String toString() {
            return "CCTV{" +
                    "kind=" + kind +
                    ", pos=" + Arrays.toString(pos) +
                    ", up=" + up +
                    ", down=" + down +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private static int n,m,min;
    private static int[] dx = {-1,1,0,0};   //  v , ^
    private static int[] dy = {0,0,1,-1};   //  > , <
    private static String[][] map;
    private static List<CCTV> list;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/baekjoon/cctv_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());       // 세로
        m = Integer.parseInt(st.nextToken());       // 가로
        min = Integer.MAX_VALUE;
        list = new ArrayList<>();
        map = new String[n][m];                     // 감시영역

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++) {
                 map[i][j] = st.nextToken();
                 if( !map[i][j].equals("0") || !map[i][j].equals("6") ) {
                     getCCTV(i,j);
                 }
            }
        }

//        confirmMap(map);
//
//        for(int i = 0 ; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        String[][] map_temp = new String[n][m];
        for(int i = 0; i < n; i++) {
            map_temp[i] = Arrays.copyOf(map[i],m);
        }
        move(0,map_temp);
        System.out.println(min);

    }

    private static void move(int cnt,String[][] map_tmp) {

        if( cnt >= list.size() ) {
            confirmMap(map_tmp);
            int count = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(map_tmp[i][j].equals("0")) {
                        count++;
                    }
                }
            }
            min = Math.min(min,count);
            return;
        }

        int dirSize = 4;
        if( list.get(cnt).kind == 2 ) dirSize = 2;
        else if( list.get(cnt).kind == 5 ) dirSize = 1;

        for(int i = 0; i < dirSize; i++) {
            String[][] map_temp = new String[n][m];
            for(int j = 0; j < n; j++){
                map_temp[j] = Arrays.copyOf(map_tmp[j],m);
            }
            Look(list.get(cnt),map_temp);
            move(cnt + 1,map_temp);
            change(list.get(cnt));
        }
    }

    private static void getCCTV(int x, int y) {
        switch ( map[x][y] ) {
            case "1":
                list.add(new CCTV(1,new int[] {x,y}));
                break;
            case "2":
                list.add(new CCTV(2,new int[] {x,y}));
                break;
            case "3":
                list.add(new CCTV(3,new int[] {x,y}));
                break;
            case "4":
                list.add(new CCTV(4,new int[] {x,y}));
                break;
            case "5":
                list.add(new CCTV(5,new int[] {x,y}));
                break;
        }
    }

    private static void Look(CCTV cctv,String[][] map_tmp) {

        for(int i = 0; i < 4; i++) {
            if( cctv.getmove()[i] ) {
                int nx = cctv.pos[0] + dx[i];
                int ny = cctv.pos[1] + dy[i];
                while ( isCheck(nx,ny) ) {
                    if( !map_tmp[nx][ny].equals("6") ) {
                        if( "12345".indexOf(map_tmp[nx][ny]) < 0 ) {
                            map_tmp[nx][ny] = "#";
                        }
                        nx += dx[i];
                        ny += dy[i];
                        continue;
                    }
                    break;
                }
            }
        }
    }

    private static void unLook(CCTV cctv,String[][] map_tmp) {

        for(int i = 0; i < 4; i++) {
            if( cctv.getmove()[i] ) {
                int nx = cctv.pos[0] + dx[i];
                int ny = cctv.pos[1] + dy[i];
                while ( isCheck(nx,ny) ) {
                    if( !map_tmp[nx][ny].equals("#")) {
                        break;
                    }
                    map_tmp[nx][ny] = "0";
                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }
    }

    private static void change(CCTV cctv) {
        switch (cctv.kind) {
            case 1:
                if( cctv.right ) {
                    cctv.right = false;
                    cctv.down = true;
                } else if( cctv.down ) {
                    cctv.down = false;
                    cctv.left = true;
                } else if( cctv.left) {
                    cctv.left = false;
                    cctv.up = true;
                } else if( cctv.up ) {
                    cctv.up = false;
                    cctv.right = true;
                }
                break;
            case 2:
                if( cctv.right & cctv.left) {
                    cctv.up = true;
                    cctv.down = true;
                    cctv.right = false;
                    cctv.left = false;
                } else {
                    cctv.up = false;
                    cctv.down = false;
                    cctv.right = true;
                    cctv.left = true;
                }
                break;
            case 3:
                if( cctv.up && cctv.right ) {
                    cctv.down = true;
                    cctv.up = false;
                } else if( cctv.right && cctv.down ) {
                    cctv.right = false;
                    cctv.left = true;
                } else if( cctv.down && cctv.left ) {
                    cctv.down = false;
                    cctv.up = true;
                } else if( cctv.left && cctv.up ) {
                    cctv.left = false;
                    cctv.right = true;
                }
                break;
            case 4:
                if( cctv.up && cctv.right && cctv.left ) {
                    cctv.down = true;
                    cctv.left = false;
                } else if( cctv.up && cctv.right && cctv.down ) {
                    cctv.left = true;
                    cctv.up = false;
                } else if( cctv.right && cctv.down && cctv.left ) {
                    cctv.up = true;
                    cctv.right = false;
                } else if( cctv.down && cctv.left && cctv.up ) {
                    cctv.down = false;
                    cctv.right = true;
                }
                break;
        }
    }

    public static boolean isCheck(int x, int y ) {
        if( x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }

    public static void confirmMap(String[][] map) {
        System.out.println("-----------------------");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}
