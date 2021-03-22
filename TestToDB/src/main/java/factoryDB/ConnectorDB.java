package factoryDB;

import java.util.List;

import model.User;

public interface ConnectorDB {
	
	List<User> getUsers();
	void createUser(User user);
	void getUserById(User user);

}
