package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import factoryDB.ConnectorDB;
import factoryDB.ConnectorDBImpl;
import model.User;

public class ConnectorDBTest {
	
	private ConnectorDB connector;
	
	@Before
	public void setUp() {
		this.connector = new ConnectorDBImpl();
	}
	
	@Test
	public void checkExistingUserById() {
		User user = new User();
		user.setIdUser(2);
		User existingUser = connector.getUserById(user);
		assertEquals(existingUser.getIdUser(), user.getIdUser());
	}
	
	@Test
	public void checkSortingListUsers() {
		List<User> list = connector.getUsers();
		List<User> oldlist = new ArrayList<>(list);
		List<User> sortedList = connector.sortUsers("age", "increase");
		for(User user : oldlist) {
			System.out.println(user);
		}
		System.out.println();
		sortedList.forEach(user -> {
			System.out.println(user);
					});
		assertNotEquals(sortedList.get(0).getIdUser(), oldlist.get(0).getIdUser());
	}
	
	@Test
	public void checkNotExistingUserById() {
		User user = new User();
		user.setIdUser(375);
		User existingUser = connector.getUserById(user);
		
		assertNotNull(existingUser);
	}
}
