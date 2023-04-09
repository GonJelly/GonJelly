package programmers.level2.pairSiso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairSiso_tmp {

	public static void main(String[] args) {
		int[] weights = {100,180,360,100,270};
		HashMap<Integer, Integer> map = new HashMap<>();
		long answer = 0;
		int[][] direct = {
			{2,3},{2,4},{3,4},
			{4,3},{4,2},{3,2}
		};

		Arrays.sort(weights);

		for( int w : weights ) {
			if( map.get(w) == null ) {
				map.put(w, 1);
			} else {
				map.put(w, map.get(w) + 1);
			}
		}

		for( int w : weights ) {

			if( map.get(w) > 1 ) {
				answer += (map.get(w) * (map.get(w) - 1)) / 2;
			}

			for( int i = 0; i < direct.length; i++ ) {
				int result = w * direct[i][0] / direct[i][1];
				int remain = w * direct[i][0] % direct[i][1];
				if( remain == 0 && map.get(result) != null ) {
					answer += map.get(w) * map.get(result);
				}
			}

			map.put(w, 0);
		}

		System.out.println(answer);
	}
}
