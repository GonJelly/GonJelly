package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class jungol_1828 {

    static class kemical implements Comparable<kemical>{

        int bottom;
        int top;

        public kemical(int bottom, int top) {
            this.bottom = bottom;
            this.top = top;
        }


        @Override
        public int compareTo(kemical o) {
            if( Integer.valueOf(this.top).compareTo(Integer.valueOf(o.top)) > 0) {
                return 1;
            } else if( Integer.valueOf(this.top).compareTo(Integer.valueOf(o.top)) < 0 ) {
                return -1;
            } else {
                if( Integer.valueOf(this.bottom).compareTo(Integer.valueOf(o.bottom)) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    private static List<kemical> list;
    private static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());            // 화힉품 갯수
        list = new ArrayList<>();                           // 화학품 저장공강
        count++;

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            list.add(new kemical(x1,y1));
        }

        Collections.sort(list);

        for(int i = 0; i < n; i++) {
            System.out.printf("%s : 최저온도 : %d    최고 온도 : %d%n",list.get(i).getClass().getName(),list.get(i).bottom,list.get(i).top);
        }

        getCount(1, list.get(0).top);
        System.out.println(count);
    }

    private static void getCount(int start, int maxTmp) {
        for(int i = start; i < list.size(); i++) {
            if( list.get(i).bottom > maxTmp ) {
                count++;
                getCount(i + 1, list.get(i).top);
                break;
            }
        }
    }
}
