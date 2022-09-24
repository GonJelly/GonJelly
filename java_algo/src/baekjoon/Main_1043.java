package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 거짓말
// 제한시간 : 2초 메모리 : 128MB
public class Main_1043 {

    static int[] people;
    static boolean[] knows;

    public static void make(int x) {
        people[x] = x;
    }

    public static int find(int x) {
        if( x == people[x] ) return x;
        else return find(people[x]);
    }

    public static void union(int a, int b) {
        if( find(a) == find(b) ) return;
        else {
            virus(find(a),find(b));
            people[find(b)] = find(a);
        }
    }
    // 1 < N , M <= 50
    // 파티에 참가하는 사람수 <= 50
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());       // 사람의 수
        int m = Integer.parseInt(st.nextToken());       // 파티의 수
        int count = m;
        people = new int[n+1];
        for(int x = 1; x <= n; x++) {
            make(x);
        }

        knows = new boolean[n + 1];           // 진실을 아는 사람
        boolean[] isTalk = new boolean[m];              // 거짓말을 말할 수 있는 파티의 수

        st = new StringTokenizer(in.readLine());        // 진실을 아는 사람들
        int k = Integer.parseInt(st.nextToken());       // 진실을 아는 사람의 수
        if( k > 0) {                                    // 진실을 아는 사람이 1이상 경우 ( 최대 => 50 )
            int first = Integer.parseInt(st.nextToken());
            knows[first] = true;
            while ( st.hasMoreTokens() ) {
                int second = Integer.parseInt(st.nextToken());
//                union( first, second );
                knows[second] = true;
            }
        }

//        System.out.println(Arrays.toString(people));

        List<int[]> party = new ArrayList<>();
        // 파티 구성원 할당 ( 50x50 ==> 2500 )
        for(int x = 0; x < m; x++) {
            st = new StringTokenizer(in.readLine());
            // 사람의 인원수 만틈 배열의 크기를 정해주기
            int idx = 0;                // index
//            boolean ischeck = false;    // 진실을 아는 사람이 포함되어 있는지 확인여부 체크
            int[] members = new int[Integer.parseInt(st.nextToken())];
            // 파티 멤버 할당
            while( st.hasMoreTokens() ) {
                int member = Integer.parseInt(st.nextToken());
                members[idx++] = member;
            }

//            Arrays.sort(members);
//            if( members.length > 1) {
                for (int y = 0; y < idx - 1; y++) {
                    int person1 = members[y];
                    int person2 = members[y + 1];
                    union(person1,person2);
//                    virus(person1,person2);
                }
//            }

            // 파티 추가
            party.add(members);
        }
//        System.out.println(Arrays.toString(knows));
        for(int[] members : party ) {
            for(int c = 0; c < members.length; c++) {
                if( knows[find(members[c])] ) {
                    count--;
                    break;
                }
            }
        }

//        System.out.println(Arrays.toString(people));

        System.out.println(count);
    }

    private static void virus(int person1, int person2) {
        if (knows[person1]) {
            knows[person2] = knows[person1];
        } else if (knows[person2]) {
            knows[person1] = knows[person2];
        }
    }
}
