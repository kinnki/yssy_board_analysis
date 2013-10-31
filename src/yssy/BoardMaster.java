package yssy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BoardMaster {

	public final static String BOARD_MASTER_START = "板主:";

	public static Account getAccount(String id) {
		Account account = new Account(id);
		String url = "http://bbs.sjtu.edu.cn/bbsqry?userid={1}".replace("{1}",
				id);
		try {
			Document document = Jsoup.connect(url).get();
			Elements elements = document.select("pre");
			String text = elements.text();
			account.setUp(getUp(text));
			account.setAge(getAge(text));
			account.setLastLoginDate(getLastLoginDate(text));
			account.setIp(getIp(text));
			account.setAstrology(getAstrology(text));
			account.setLife(getLife(text));
			account.setPost(getPost(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return account;
	}

	public static int getUp(String text) {
		int up = -1;
		String regex = "(?<=共上站\\s)\\d+(?=\\s次)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			up = Integer.parseInt(matcher.group());
		}
		return up;
	}

	public static int getAge(String text) {
		int age = -1;
		String regex = "(?<=网龄\\[)\\d+(?=\\]天)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			age = Integer.parseInt(matcher.group());
		}
		return age;
	}

	public static String getAstrology(String text) {
		String astrology = new String();
		String regex = "(?<=\\[)[^\\[\\]]+座(?=\\])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			astrology = matcher.group();
		}
		return astrology;
	}

	public static String getLastLoginDate(String text) {
		String lastLoginDate = new String();
		String regex = "(?<=上\\s次\\s在:\\s\\[)[^\\[\\]]+(?=\\])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			lastLoginDate = matcher.group();
		}
		return lastLoginDate;
	}

	public static String getIp(String text) {
		String ip = new String();
		String regex = "(?<=从\\s\\[)[^\\[\\]]+(?=\\]\\s到本站一游)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			ip = matcher.group();
		}
		return ip;
	}

	public static int getLife(String text) {
		int life = -9999;
		String regex = "(?<=生命力:\\[)\\d+(?=\\])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			life = Integer.parseInt(matcher.group());
		}
		return life;
	}

	public static int getPost(String text) {
		int post = -1;
		String regex = "(?<=文章:\\[)\\d+(?=\\])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			post = Integer.parseInt(matcher.group());
		}
		return post;
	}

	public static ArrayList<String> getBoardMasterList(String boardName) {
		ArrayList<String> boardMasterList = new ArrayList<String>();
		String url = "http://bbs.sjtu.edu.cn/bbsdoc,board,{1}.html".replace(
				"{1}", boardName);
		try {
			Document document = Jsoup.connect(url).get();
			Elements elements = document.select("table");
			for (Element element : elements) {
				String text = element.text();
				if (text.startsWith(BOARD_MASTER_START)) {
					text = text.substring(0, text.length() - 14);
					String regex = "\\b\\w+\\b";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(text);
					while (matcher.find()) {
						boardMasterList.add(matcher.group());
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boardMasterList;
	}

	public static void main(String[] args) {
		getAccount("kinki");
	}

}
