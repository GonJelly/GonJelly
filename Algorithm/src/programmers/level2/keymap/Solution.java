package programmers.level2.keymap;

import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {
		// 알파벳 대문자로만 이루어져 있습니다.
		String[] keymap = {
			"AA"
		};
		String[] targets = {
			"ABCD"
		};

		System.out.println(Arrays.toString(solution(keymap, targets)));
	}

	public static int[] solution(String[] keymap, String[] targets) {

		int[] answer = new int[targets.length];
		// 대문자 문자들의 최소 클릭 수를 저장할 배열 [없으면 0]
		int[] clickMin = new int[26];
		for( int i = 0; i < keymap.length; i++) {
			// 해당 키의 문자를 배열로 치횐
			char[] syntax = keymap[i].toCharArray();
			for(int j = 0; j < syntax.length; j++) {
				// 대문자를 숫자로 치환
				int ch = syntax[j] - '@';
				// 해당 문자의 최소클릭 수가 0면 변경
				if( clickMin[ch] == 0 ) {
					clickMin[ch] = j + 1;
				}
				// 해당 키의 문자키 숫자가 최소클릭 수라면 변경
				else if( clickMin[ch] > j + 1) {
					clickMin[ch] = j + 1;
				}
			}
		}

		for(int i = 0; i < targets.length; i++) {
			char[] temp = targets[i].toCharArray();
			answer[i] = counting(clickMin, temp);
		}

		return answer;
	}

	public static int counting(int[] clickMin, char[] temp) {
		int result = 0;

		for( int j = 0; j < temp.length; j++) {
			int idx = temp[j] - '@';
			int count = clickMin[idx];
			if( count == 0 ) {
				result = -1;
				break;
			}
			result += count;
		}
		return result;
	}
}
