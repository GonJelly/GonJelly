package baekjoon.label3000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_3040 {

    static int[] dwarfSelect;
    static List<Integer> dwarfs;

    public static void main(String[] args) throws IOException {
//        FileInputStream fis = new FileInputStream("./study_algorithm/res/baekjoon/SnowWhite_input.txt");
//        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // 초기화
        dwarfs = new ArrayList<>();
        dwarfSelect = new int[9];
        // 난쟁이 모자 값 할당
        for(int i = 0; i < 9; i++) {
            dwarfs.add(Integer.parseInt(in.readLine()));
        }

        myDwarf(0,0);
//        dwarfs.forEach(System.out::println);
        in.close();
    }

    private static void myDwarf(int idx,int cnt) {
        if( cnt == 7 ) {
            if( myDwarfCheck() ) {
                for(int i = 0; i < 9; i++) {
                    if( dwarfSelect[i] == 1 ) {
                        System.out.println(dwarfs.get(i));
                    }
                }
            }
            return;
        }

        if( idx >= 9) return;

        dwarfSelect[idx] = 1;
        myDwarf(idx + 1,cnt + 1);
        dwarfSelect[idx] = 0;
        myDwarf(idx + 1, cnt);
    }

    private static boolean myDwarfCheck() {
        int total = 0;
        for(int i = 0; i < 9; i++) {
            if( dwarfSelect[i] == 1 ) {
                total += dwarfs.get(i);
            }
        }

        if( total == 100 ) return true;
        else return false;

    }
}
