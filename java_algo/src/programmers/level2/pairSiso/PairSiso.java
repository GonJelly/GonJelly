package programmers.level2.pairSiso;

import java.util.Arrays;
import java.util.HashMap;

public class PairSiso {

	public static void main(String[] args) {
		int[] weights = {100,180,360,100,270};
		long answer = 0;

		Arrays.sort(weights);

		for( int i = 0; i < weights.length - 1; i++ ) {
			for( int j = i + 1; j < weights.length; j++ ) {
				if( weights[i] == weights[j] ) {
					answer++;
				} else if (
					weights[i] * 2 == weights[j] * 3 ||
					weights[i] * 2 == weights[j] * 4 ||
					weights[i] * 3 == weights[j] * 4 ||
					weights[i] * 4 == weights[j] * 3 ||
					weights[i] * 4 == weights[j] * 2 ||
					weights[i] * 3 == weights[j] * 2
				) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}
}
