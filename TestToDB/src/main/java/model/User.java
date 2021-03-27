package model;

public class User {

	private int idUser;
	private String username;
	private int age;
	private boolean exists;

	public User(int idUser, String username, int age, boolean exists) {
		this.idUser = idUser;
		this.username = username;
		this.age = age;
		this.exists = exists;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + (exists ? 1231 : 1237);
		result = prime * result + idUser;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (exists != other.exists)
			return false;
		if (idUser != other.idUser)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

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
		return "User [idUser=" + idUser + ", username=" + username + ", age=" + age + ", exists=" + exists + "]";
	}

	public User() {
	}

}
