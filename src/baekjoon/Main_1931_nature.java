package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1931_nature {

    private static int n,max;
    private static LinkedList<int[]> conference;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./algorithm_java/res/baekjoon/conference_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));;
        long start = System.nanoTime();
        n = Integer.parseInt(in.readLine());
        conference = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            conference.add(i, new int[] {x,y});
        }

        Collections.sort(conference, (o1, o2) -> {
            if(o1[1] == o2[1]) return Integer.compare(o1[0],o2[0]);
            return Integer.compare(o1[1],o2[1]);
        });
//
//        for(int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(conference.get(i)));
//        }


        combination2(0,0,0);
        System.out.println(max);
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + "ms");
        in.close();
    }
    // 김명진님 것을 참고한 부분...
    private static void combination1(int endTime, int cnt, int use) {
        max = Math.max(max,use);
        System.out.println(cnt +":::"+ use +":::"+ endTime);
        for(int i = cnt; i < n ; i++) {
            if( conference.get(i)[0] >= endTime ) {
                combination1(conference.get(i)[1],i + 1, use + 1);
                break;
            }
        }
    }
    // 제가 작성한 부분..
    private static void combination2(int cnt, int use,int end) {

        if( cnt == n) {
            max = Math.max(max,use);
            return;
        }
        System.out.println(cnt +":::"+ use +":::"+ end);
        if( use == 0) {
            combination2(cnt + 1, use + 1, conference.get(cnt)[1]);
            return;
        }

        if( conference.get(cnt)[0] >= end ) {
            combination2(cnt + 1, use + 1, conference.get(cnt)[1]);
        } else {
            combination2(cnt + 1, use, end);
        }
    }
}
