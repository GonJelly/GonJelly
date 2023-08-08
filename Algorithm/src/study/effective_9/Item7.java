package study.effective_9;

import java.util.HashMap;
import java.util.Map;

public class Item7 {

	public static void main(String[] args) {
		Cashe cashe = new Cashe();
		cashe.initMap();
		cashe.forEachDisplay();
	}

}

class Cashe {

	private Map<String, String> map = new HashMap<>();

	public void initMap() {
		map.put("필라테스", "여자들에게 좋은 운동이에요. 체형교정에 좋음");
		map.put("헬스", "근력을 키우고 싶은 사람들에게 강추");
		map.put("주짓수", "호신술에 좋음, 자신의 몸은 자신이 지키자.");
	}

	public Map<String, String> getCashe() {
		return map;
	}

	public void forEachDisplay() {
		for(String key : map.keySet()) {
			System.out.println(map.get(key));
		}
	}


}
