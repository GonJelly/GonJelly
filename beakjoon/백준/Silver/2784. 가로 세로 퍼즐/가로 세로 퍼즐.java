import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int LEN = 6;
    static List<String> result;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        words = new String[LEN];
        result = new ArrayList<>();

        for(int r = 0; r < words.length; r++) {
            String in = br.readLine();
            words[r] = in;
        }
        dfs(0,0,"");
        // 사전순서대로 정렬
        Collections.sort(result);
        // 퍼즐을 완성하지 못하면 0을 출력한다.
        if( result.isEmpty() ) {
            System.out.println(0);
        } else {
            String word = result.get(0);
            for(int i = 0; i < 9; i = i + 3) {
                System.out.println(word.substring(i, i + 3));
            }
        }
    }

    private static void dfs(int cnt,int flag, String store) {
        // 문자열을 완성된 상황
        if( cnt >= 3 ) {
            // 가로 세로의 단어를 저장할 공간
            List<String> temp = new ArrayList<>();
            // 가로의 단어를 구한다.
            for(int i = 0; i < 9; i = i + 3) {
                temp.add(store.substring(i, i + 3));
            }
            // 세로의 단어를 구한다.
            for(int r = 0; r < 3; r++) {
                StringBuilder word = new StringBuilder();
                for(int c = r; c < 9; c = c + 3) {
                    word.append(store.charAt(c));
                }
                // 완성된 단어를 저장한다.
                temp.add(word.toString());
            }
            // 주어진 단어를 하나씩 제거해간다.
            for(int x = 0; x < words.length; x++) {
                temp.remove(words[x]);
            }
            // 만약 모든 단어를 삭제되었다면 주어진 단어를 전부 사용햇다는 뜻
            if( temp.size() == 0 ) {
                result.add(store);
            }

            return;
        }

        for(int i = 0; i < words.length; i++) {
            // 해당 숫자를 사용여부 검사
            if( ( flag & (1 << i) ) > 0 ) continue;
            dfs(cnt + 1, flag | (1 << i), store + words[i] );
        }
    }
}