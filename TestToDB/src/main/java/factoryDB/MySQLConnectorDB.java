package factoryDB;

import java.util.ArrayList;
import java.util.List;

import model.User;

public class MySQLConnectorDB implements ConnectorDB {

	@Override
	public List<User> getUsers() {
		return new ArrayList<User>();
	}

	@Override
	public void addUser(User user) {
		
	}

	@Override
	public void getUserById(User user) {
		
	}

	@Override
	public void removeUser(User user) {
		
	}

	@Override
	public List<User> sortUsers(String field, String howToChange) {

		return new ArrayList<User>();
	}

}
