package Utils;

import java.util.ArrayList;
import java.util.List;

//通过工具类 获取权限列表数据
public class PriList {
	
	private static List<String> priList = new ArrayList<String>();

	public static List<String> getPriList() {
		return priList;
	}

	public static void setPriList(List<String> priList) {
		PriList.priList = priList;
	}
	
	
}
