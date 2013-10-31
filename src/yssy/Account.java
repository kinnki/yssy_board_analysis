package yssy;

public class Account {
	private String name = new String();
	private int up = 0;
	private int age = 0;
	private String lastLoginDate = new String();
	private String ip = new String();
	private int life = 0;
	private int post = 0;
	private String astrology = new String();

	public Account() {

	}

	public Account(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public String getAstrology() {
		return astrology;
	}

	public void setAstrology(String astrology) {
		this.astrology = astrology;
	}

	public String toString() {
		return this.name + "," + this.up + "," + this.age + ","
				+ this.astrology + "," + this.lastLoginDate + "," + this.ip
				+ "," + this.life + "," + this.post;
	}
}
