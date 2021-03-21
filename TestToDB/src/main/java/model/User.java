package model;

public class User {

	private int idUser;
	private String name;
	private int age;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", age=" + age + "]";
	}

	public User(int idUser, String name, int age) {
		this.idUser = idUser;
		this.name = name;
		this.age = age;
	}

	public User() {
	}

}
