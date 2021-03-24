package factoryDB;

import java.util.List;

import model.User;

public interface ConnectorDB {
	
	List<User> getUsers();
	void addUser(User user);
	void getUserById(User user);

}
