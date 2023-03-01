import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public long solution(int[] weights) {

		long answer = 0;

		Arrays.sort(weights);
		int count = 0;
		for( int i = 0; i < weights.length - 1; i++ ) {

			if ( i > 0 && weights[i] == weights[i - 1] ) {
				count--;
				answer += count;
				continue;
			}

			int left = i + 1;
			int right = weights.length - 1;
			int middle = left;
			while ( left < right ) {
				int mid = left + ( right - left ) / 2;
				if( weights[mid] > weights[i] * 2 ) {
					middle = mid;
					break;
				} else {
					left = mid + 1;
					middle = left;
				}
			}

			count = 0;

			for( int j = middle; j > i; j-- ) {
				if(
					weights[i] == weights[j] ||
					weights[i] * 2 == weights[j] ||
					weights[i] * 3 == weights[j] * 2 ||
					weights[i] * 4 == weights[j] * 3

				) {
					count++;
				}
			}

			answer += count;
		}
        
		return answer;
	}
}