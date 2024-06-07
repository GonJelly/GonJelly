import java.util.Map;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        // 호명할 횟수
        int call = callings.length;
        // 선수의 등수를 저장할 Map
        Map<String, Integer> rank = new HashMap();
        // 해당 선수의 등수를 별도로 저장 ( 50,000 )
        for(int r = 0; r < players.length; r++) {
            rank.put(players[r],r);
        }
        
        // 해설진이 호명한 선수들 만큼 반복문을 수행
        for(int i = 0;  i < call; i++) {
            // 호명한 선수의 이름의 인덱스를 찾고
            String player = callings[i];
            int idx = rank.get(player);
            // 1등 즉 인덱스가 0인 선수는 호명되지 않는다.
            // 해당 인덱스의 앞에 번호와 스위칭을 해준다.
            String temp = players[idx - 1];
            players[idx - 1] = players[idx];
            players[idx] = temp;
            // 등수도 변경
            rank.put(player, idx - 1);
            rank.put(temp, idx);
        }
        return answer = players;
    }
}