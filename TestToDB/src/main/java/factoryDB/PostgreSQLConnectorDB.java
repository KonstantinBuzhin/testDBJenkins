package factoryDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class PostgreSQLConnectorDB implements ConnectorDB {

	// Database credentials
	static final String DB_URL = "jdbc:postgresql://172.25.1.5/testDB";
	static final String USER = "postgres";
	static final String PASS = "password";

	@Override
	public List<User> getUsers() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
			e.printStackTrace();
		}
		System.out.println("PostgreSQL JDBC Driver successfully connected");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}

		Statement stmt;
		List<User> listUsers = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from users");

			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt("id_User"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				listUsers.add(user);

			}

			rs.close();

			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.println("You successfully connected to database now");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listUsers;
	}

}
