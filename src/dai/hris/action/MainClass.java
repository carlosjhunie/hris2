package dai.hris.action;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
	public static void main(String[] args) {
		String str = "one,two,three,four,five,";
		System.out.println(str.split(",").length);
		
		List<String> strList = new ArrayList<String>();
		strList.add("one");
		strList.add("two");
		strList.add("three");
		strList.add("four");
		strList.add("five");
		System.out.println(strList);
	}
}
