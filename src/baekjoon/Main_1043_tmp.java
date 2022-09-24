package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 거짓말
// 제한시간 : 2초 메모리 : 128MB
public class Main_1043_tmp {
    // 1 < N , M <= 50
    // 파티에 참가하는 사람수 <= 50
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());       // 사람의 수
        int m = Integer.parseInt(st.nextToken());       // 파티의 수

        boolean[] knows = new boolean[n + 1];           // 진실을 아는 사람
        boolean[] isTalk = new boolean[m];              // 거짓말을 말할 수 있는 파티의 수

        st = new StringTokenizer(in.readLine());        // 진실을 아는 사람들
        int k = Integer.parseInt(st.nextToken());       // 진실을 아는 사람의 수
        if( k > 0) {                                    // 진실을 아는 사람이 1이상 경우 ( 최대 => 50 )
            for( int x = 0; x < k; x++) {
                int idx = Integer.parseInt(st.nextToken());
                knows[idx] = true;                          // 진실을 알고 있으면 true 처리
            }
        }

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
                // 진실을 알고 있는 사람이 있는지 확인
                if( knows[member] ) {
                    isTalk[x] = true;
                }
            }
            // 해당 파티에서는 진실을 말했다면
            if( isTalk[x] ) {
                // 해당 파티에 참가한 사람은 전부 진실을 알고 있다는 것이 된다.
                for( int tmp : members ) {
                    knows[tmp] = true;
                }
            }
            // 파티 추가
            party.add(members);
        }

//        party.forEach( arr -> System.out.println(Arrays.toString(arr)));
        // 진실을 알고있는 사람이 있는 파티에서는 거짓말을 할 수 없기 때문에
        // 전부 true로 바꿔준다.
        // 50x50 => 2500
        for( int r = 0; r < party.size(); r++) {
            for(int c = 0; c < party.get(r).length; c++) {
                if( knows[party.get(r)[c]] ) {
                    isTalk[r] = true;
                }
            }
        }

        int count = 0;
        for( int x = 0; x < m; x++) {               // 최대 50
            if( !isTalk[x] ) {
                count++;
            }
        }

        System.out.println(count);
    }
}
