package factoryDB;

import java.util.Arrays;
import java.util.List;

import model.User;

public class ConnectorDBImpl implements ConnectorDB {

	private List<User> listUsers;

	public ConnectorDBImpl() {
		this.listUsers = Arrays.asList(new User(1, "sergey", 40, true), new User(2, "dima", 15, true),
				new User(3, "max", 22, true), new User(4, "andrey", 54, true), new User(5, "admin", 33, true));
	}

	@Override
	public List<User> getUsers() {

		return listUsers;
	}

	@Override
	public List<User> sortUsers(String field, String howToChange) {
		if (howToChange.equals("increase")) {
			switch (field) {
			case "username":
				listUsers.sort((user1, user2) -> user1.getUsername().compareTo(user2.getUsername()));
				break;
			case "age":
				listUsers.sort((user1, user2) -> user1.getAge() - user2.getAge());
				break;

			default:
				listUsers.sort((user1, user2) -> user1.getIdUser() - user2.getIdUser());
			}
		} else {
			switch (field) {
			case "username":
				listUsers.sort((user1, user2) -> user2.getUsername().compareTo(user1.getUsername()));
				break;
			case "age":
				listUsers.sort((user1, user2) -> user2.getAge() - user1.getAge());
				break;

			default:
				listUsers.sort((user1, user2) -> user2.getIdUser() - user1.getIdUser());
			}
		}

		return listUsers;
	}

	@Override
	public void addUser(User user) {
		

	}

	@Override
	public void removeUser(User user) {
		

	}

	@Override
	public User getUserById(User user) {
		return listUsers.stream().filter(selectedUser -> selectedUser.getIdUser()==user.getIdUser()).findAny().orElse(new User());

	}

}
