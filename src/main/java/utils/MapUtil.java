package utils;

import java.util.Map;

public class MapUtil {

	public static boolean isEmptyMap(Map map) {
		boolean res = false ;
		if(map == null || map.isEmpty()) {
			res = true;
		}
		return res;
	}
	
	public static boolean isNotEmptyMap(Map map) {
		return !isEmptyMap(map);
	}
}
