package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1931_nature2 {

    private static int n;
    private static LinkedList<int[]> conference;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        long start = System.nanoTime();
        int count = 0;
        int endTime = 0;
        conference = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            conference.add(i, new int[]{x, y});
        }

        Collections.sort(conference, (o1, o2) -> {
            if (o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
            return Integer.compare(o1[1], o2[1]);
        });

        for (int i = 0; i < n; i++) {
            if (conference.get(i)[0] >= endTime) {
                count++;
                endTime = conference.get(i)[1];
            }
        }

        System.out.println(count);
        long end = System.nanoTime();
        System.out.println((end - start ) / 1000 + "ms");
        in.close();
    }
}
