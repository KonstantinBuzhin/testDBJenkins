package model;

public class User {

	private int idUser;
	private String username;
	private int age;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", age=" + age + "]";
	}

	public User(int idUser, String username, int age) {
		this.idUser = idUser;
		this.username = username;
		this.age = age;
	}

	public User() {
	}

}
