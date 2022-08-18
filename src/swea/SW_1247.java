package swea;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class SW_1247 {

    private static int n, min, count;
    private static List<Point> consumer;
    private static Point company, home;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for( int t = 1; t <= T; t++) {
            count = 0;
            n = Integer.parseInt(in.readLine());
            min = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(in.readLine());

            company = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            home = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            System.out.println(company);
            System.out.println(home);
            consumer = new ArrayList<Point>();
            for(int i = 0; i < n; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                consumer.add(new Point(x,y));
            }

            Collections.sort(consumer, (o1, o2) -> {
                int diffA = diff(company.x,o1.x,company.y,o1.y);
                int diffB = diff(company.x,o2.x,company.y,o2.y);
                return diffA - diffB;
            });

            for(Point point : consumer) {
                System.out.println(point);
            }

            suffle(0,null, 0, 0);
            sb.append("#").append(t).append(" ").append(min+"\n");
        }

        System.out.println(sb);

    }

    private static void suffle(int cnt,Point point ,int flag, int distance) {

        if( cnt == n) {
            int tmp = diff(home.x, point.x,home.y,point.y);
            min = Math.min(min,distance + tmp);
        }

        for(int i = 0; i < n; i++) {
            if( (flag & 1<<i) != 0) continue;
//            System.out.println(cnt);
            if ( cnt == 0 ) {
                int tmp = diff(company.x, consumer.get(i).x, company.y, consumer.get(i).y);
                suffle(cnt + 1, consumer.get(i), flag | 1 << i, distance + tmp);
            } else {
                int tmp = diff(point.x, consumer.get(i).x,point.y,consumer.get(i).y);
                suffle(cnt + 1, consumer.get(i) ,flag | 1<<i, distance + tmp);
            }
        }
    }

    private static int diff(int x1, int x2, int y1, int y2) {
        return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
    }
}
