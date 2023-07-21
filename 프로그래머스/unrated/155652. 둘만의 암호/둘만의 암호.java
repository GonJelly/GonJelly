class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        // 문자를 저장할 공간
        StringBuilder sb = new StringBuilder();
        // s 문자열에 각 문자들을 가져온다.
        for(int r = 0; r < s.length(); r++) {
            // 제외된 만큼 추가적으로 올려줄 값
            int add = 0;
            // s 문자열에 i번째 문자
            char _s = s.charAt(r);
            for(int c = 0; c < index; c++) {
                _s += 1;
                
                if(_s > 'z') {
                    _s -= 26;
                }
                
                // 유효성 검사 skip 문자인지 확인
                while(skip.contains(String.valueOf(_s))) {
                    _s += 1;
                    if(_s > 'z') {
                        _s -= 26;
                    }
                }
            
            }
            
            sb.append(_s);
        }
        answer = sb.toString();
        return answer;
    }
}