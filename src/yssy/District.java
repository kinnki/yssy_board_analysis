package yssy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class District {
	public final static String[] DISTRICTS = { "District0", "District1",
			"District2", "District3", "District4", "District5", "District6",
			"District7", "District8", "District9", "DistrictA", "DistrictB" };

	public static HashMap<String, ArrayList<String>> getDistrictBoardMap() {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < DISTRICTS.length; i++) {
			String district = DISTRICTS[i];
			ArrayList<String> boardList = Board.getDistrictBoardList(String
					.valueOf(i));
			map.put(district, boardList);
		}
		return map;
	}

	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> map = getDistrictBoardMap();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			System.out.println(key + ": " + map.get(key));
		}
	}
}
