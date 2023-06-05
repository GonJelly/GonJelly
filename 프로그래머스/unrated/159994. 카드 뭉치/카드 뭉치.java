class Solution {
    
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        
        String answer = "Yes";
        
        // 카드1의 길이
        int first = 0;
        // 카드2의 길이
        int second = 0;
        // 목표로한 문자배열에 길이
        int third = goal.length;
        
        // goal을 만들 수 있는 단어가 있는지 확인
        for(int i = 0; i < third; i++) {
            String str = goal[i];
            // 카드1에 문자가 존재하는지 확인
            if( first < cards1.length && cards1[first].equals(str) ) {
                first++;
                continue;
            }
            // 카드2에 문자가 존재하는지 확인
            if( second < cards2.length && cards2[second].equals(str) ) {
                second++;
                continue;
            }
            // 만약 존재하지 않다면 No 리턴
            answer = "No";
            break;
        }
        
        
        return answer;
    }
}