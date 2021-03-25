package factoryDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class PostgreSQLConnectorDB implements ConnectorDB {

	// Database credentials
	static final String DB_URL = "jdbc:postgresql://192.168.1.103:5432/testingdb";
	static final String USER = "postgres";
	static final String PASS = "postgres";

	@Override
	public List<User> getUsers() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Statement stmt;
		List<User> listUsers = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from users");

			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setAge(rs.getInt("age"));
				listUsers.add(user);
			}

			rs.close();

			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listUsers;
	}

	@Override
	public void addUser(User user) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement pst = connection.prepareStatement("INSERT INTO users(username, age) VALUES(?, ?)");
			pst.setString(1, user.getUsername());
			pst.setInt(2, user.getAge());
			pst.execute();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getUserById(User user) {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Statement stmt;
		try {
			stmt = connection.createStatement();

			stmt.execute("CREATE OR REPLACE PROCEDURE updateIvan(theAge int) AS '" + " BEGIN "
					+ "    UPDATE users SET age = theAge WHERE name = 'ivan';" + "    COMMIT; "
					+ " END;' LANGUAGE plpgsql");
			stmt.close();

			// Procedure call.
			CallableStatement proc = connection.prepareCall("{ ? = call updateIvan() }");
			proc.registerOutParameter(1, Types.OTHER);
			proc.execute();
			ResultSet results = (ResultSet) proc.getObject(1);
			while (results.next()) {
				// do something with the results...
			}
			results.close();
			proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeUser(User user) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement pst = connection.prepareStatement("DELETE FROM users WHERE id = ?");
			pst.setInt(1, user.getIdUser());
			pst.execute();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<User> sortUsers(String field, String howToChange) {
		List<User> listUsers = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String change = null;
		
		if (howToChange.equals("increase")) {
			change = "ASC";
		} else if (howToChange.equals("decrease")) {
			change = "DESC";
		}
		
		if (change != null) {
			try {

				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM USERS ORDER BY "+field+" "+change);
				while (rs.next()) {
					User user = new User();
					user.setIdUser(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setAge(rs.getInt("age"));
					System.out.println(user);
					listUsers.add(user);
				}

				rs.close();
				st.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUsers;

	}

}
