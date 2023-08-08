package study.effective_9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Item57 {

	public static void main(String[] args) {

		List<Integer> c = new ArrayList();
		for(Iterator<Integer> i = c.iterator(); i.hasNext();) {
			int num = i.next();
		}

		for(Iterator<Integer> i = c.iterator(); i.hasNext();) {
			int num = i.next();
		}
	}

}
