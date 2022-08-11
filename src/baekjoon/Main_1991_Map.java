package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1991_Map {

    static Map<String,char[]> nodes;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int height = Integer.parseInt(in.readLine());

        nodes = new HashMap<>();
        for(int i = 0; i < height; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String key = st.nextToken();
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            nodes.put(key,new char[]{left,right});
        }

        preOrder('A');
        System.out.println();
        inOrder('A');
        System.out.println();
        postOrder('A');
    }

    private static void preOrder(char node) {

        char left  = nodes.get(String.valueOf(node))[0];
        char right = nodes.get(String.valueOf(node))[1];

        System.out.print(node);
        if( left != '.' ) preOrder(left);
        if( right != '.' ) preOrder(right);

    }
    private static void inOrder(char node) {

        char left  = nodes.get(String.valueOf(node))[0];
        char right = nodes.get(String.valueOf(node))[1];

        if( left != '.' ) inOrder(left);
        System.out.print(node);
        if( right != '.' ) inOrder(right);

    }
    private static void postOrder(char node) {

        char left  = nodes.get(String.valueOf(node))[0];
        char right = nodes.get(String.valueOf(node))[1];

        if( left != '.' ) postOrder(left);
        if( right != '.' ) postOrder(right);
        System.out.print(node);

    }

}
