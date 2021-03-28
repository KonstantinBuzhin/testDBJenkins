package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import factoryDB.PostgreSQLConnectorDB;

import model.User;
import pages.HomePage;

public class PostgreSQLConnectorDBTest {

	@Mock
	private PostgreSQLConnectorDB postgresConnector;
	
	@Spy
	private HomePage page;
	
	public PostgreSQLConnectorDBTest() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void checkGetUsers() {
		given(postgresConnector.getUsers()).willReturn(Arrays.asList(new User(1, "sergey", 40, true), new User(2, "dima", 15, true),
				new User(3, "max", 22, true), new User(4, "andrey", 54, true), new User(5, "admin", 33, true)));
		assertNotNull(postgresConnector.getUsers().get(3));
		assertEquals(postgresConnector.getUsers().get(4).getAge(), 33);
	}
	
	@Test
	public void checkEmptyListUsers() {
		List<User> list = new ArrayList<>();
		given(postgresConnector.getUsers()).willReturn(list);
		assertNotNull(postgresConnector.getUsers());
		assertEquals(postgresConnector.getUsers().size(),0);
	}
	
	@Test
	public void checkHomePageMethod() {
		assertNotEquals(page.getTableRowUser(new User()).length(), new StringBuilder("").length());
		verify(page).getTableRowUser(new User());
	}
}
