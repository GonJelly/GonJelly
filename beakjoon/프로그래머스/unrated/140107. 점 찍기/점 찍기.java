class Solution {
    
    public long solution(int k, int d) {
        long answer = 0;
        
		for( int x = 0; x <= d; x += k ) {
			double y = Math.sqrt(Math.pow(d,2) - Math.pow(x,2));
			answer += (int) y / k + 1;
		}
        
        return answer;
    }
}