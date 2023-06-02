package baekjoon;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main_2784 {

	static HashMap<String, Integer> words;	// 단어들의 갯수을 저장할 리스트
	static ArrayList<String> keys;	// 단어들의 갯수을 저장할 리스트
	static boolean[] checked;		// 해당 단어가 들어갔는지 확인하는 배열
	static Set<String> count;				// 가능한 퍼즐의 갯수

	public static void main(String args[]) throws IOException {

		// 단어를 받아와야한다.
		BufferedInputStream bis = new BufferedInputStream(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(bis));
		// 초기화
		words = new HashMap<>();
		keys = new ArrayList<>();
		checked = new boolean[6];
		count = new HashSet<>();
		// 3x3 퍼즐 초기화
		char[][] puzzle = new char[3][3];
		// 6개의 단어를 리스트에 저장
		for(int i = 0; i < 6; i++) {
			String in = br.readLine();
			// 3x3 가로 세로 퍼즐에 놓을 수 있는지 확인
			if(in.length() < 3 || in.length() > 3) {
				System.out.print(0);
				return;
			}
			// 이미있는 단어인지 아니면 새로운 단어인지 확인
			words.put(in,words.get(in) == null ? 1 : words.get(in) + 1);
			keys.add(in);
		}
		// System.out.print(keys);
		dfs(0,puzzle);
		List<String> result = count.stream().collect(Collectors.toList());
		result.sort((o1, o2) -> {
			for(int idx = 0; idx < o1.length(); idx++) {
				if (o1.charAt(idx) - o2.charAt(idx) > 0) {
					return 1;
				} else if (o1.charAt(idx) - o2.charAt(idx) < 0) {
					return -1;
				}
			}
			return 0;
		});
		if(result.size() == 0) {
			System.out.println(0);
		} else {
			String s1 = result.get(0);
			for(int i = 0; i < 9; i = i + 3) {
				System.out.println(s1.substring(i, i + 3));
			}
		}
	}

	private static void dfs(int cnt, char[][] puzzle) {

		// 기저조건 : 퍼즐에 전부 단어를 넣었을 때
		if( cnt >= 3) {
			// 원본하고 비교할 저장소
			HashMap<String, Integer> temp = new HashMap<>();
			for(int i = 0; i < 3; i++) {
				StringBuilder horizen = new StringBuilder();
				StringBuilder vertical = new StringBuilder();
				for(int j = 0; j < 3; j++) {
					horizen.append(puzzle[i][j]);
					vertical.append(puzzle[j][i]);
				}
				// System.out.printf("#%d horizen : %s , vertical : %s \n", i, horizen, vertical);
				temp.put(horizen.toString(),temp.get(horizen.toString()) == null ? 1 : temp.get(horizen.toString()) + 1);
				temp.put(vertical.toString(),temp.get(vertical.toString()) == null ? 1 : temp.get(vertical.toString()) + 1);
			}
			Iterator<String> iterator = words.keySet().iterator();
			while(iterator.hasNext()) {
				String key = iterator.next();
				// 단어가 있는지 확인
				if(temp.get(key) == null) {
					return;
				}
				// 갯수가 맞는지 확인
				if(temp.get(key).intValue() != words.get(key).intValue()) {
					return;
				}
			}

			StringBuilder st = new StringBuilder();
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {
					st.append(puzzle[r][c]);
				}
			}
			// System.out.println(st.toString());
			count.add(st.toString());
			return;
		}

		// 단어를 하나씩 넣어본다.
		for(int i = 0; i < keys.size(); i++) {
			// 사용하지 않은 단어를 사용하게끔
			if(checked[i]) continue;
			checked[i] = true;
			puzzle[cnt] = keys.get(i).toCharArray();
			dfs(cnt + 1, puzzle);
			checked[i] = false;
		}

	}
}
