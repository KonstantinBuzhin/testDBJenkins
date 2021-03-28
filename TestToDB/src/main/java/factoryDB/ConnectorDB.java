package factoryDB;

import java.util.List;

import model.User;

public interface ConnectorDB {
	
	List<User> getUsers();
	List<User> sortUsers(String field, String howToChange);
	void addUser(User user);
	void removeUser(User user);
	User getUserById(User user);

}
