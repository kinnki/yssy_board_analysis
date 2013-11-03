package yssy;

import java.util.ArrayList;

public class Analysis {

	public static void main(String[] args) {
		for (int i = 0; i < District.DISTRICTS.length; i++) {
			String district = District.DISTRICTS[i];
			ArrayList<String> boardList = Board.getDistrictBoardList(String
					.valueOf(i));
			for (String boardName : boardList) {

				ArrayList<String> boardMasterList = BoardMaster
						.getBoardMasterList(boardName);
				if (boardMasterList.isEmpty()) {
					System.out.println(district + "," + boardName);
				}
				for (String masterName : boardMasterList) {
					Account account = BoardMaster.getAccount(masterName);
					System.out.println(district + "," + boardName + ","
							+ account.toString());
				}
			}
		}

	}
}
