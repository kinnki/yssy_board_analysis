package yssy;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Board {
	public final static String bbssubboard = "bbssubboard";

	public static ArrayList<String> getDistrictBoardList(String districtId) {
		ArrayList<String> boardList = new ArrayList<String>();
		String url = "http://bbs.sjtu.edu.cn/bbsboa?sec={1}".replace("{1}",
				districtId);

		try {
			Document document = Jsoup.connect(url).get();
			Elements elements = document.select("tr");
			elements.remove(0);
			for (Element element : elements) {
				Element a = element.select("a").get(0);
				String href = a.attr("href");
				String name = a.text();
				if (href.startsWith(bbssubboard)) {
					boardList.addAll(getSubBoardList(name));
				} else {
					boardList.add(name);
				}
//				System.out.println(a.outerHtml());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return boardList;
	}

	private static ArrayList<String> getSubBoardList(String folder) {
		ArrayList<String> boardList = new ArrayList<>();
		String url = "http://bbs.sjtu.edu.cn/bbssubboard,name,{1}.html".replace("{1}",folder);

		try {
			Document document = Jsoup.connect(url).get();
			Elements elements = document.select("tr");
			elements.remove(0);
			for (Element element : elements) {
				Element a = element.select("a").get(0);
				String href = a.attr("href");
				String name = a.text();
				if (href.startsWith(bbssubboard)) {
					boardList.addAll(getSubBoardList(name));
				} else {
					boardList.add(name);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return boardList;
	}

	public static void main(String[] args) {
		System.out.println(getDistrictBoardList("0").size());
	}

}
