package factoryDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import connectionPool.DBCPDataSource;
import model.User;

public class PostgreSQLConnectorDB implements ConnectorDB {

	@Override
	public List<User> getUsers() {
		List<User> listUsers = new ArrayList<>();
		try (Connection connection = DBCPDataSource.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * from users")) {

			while (rs.next()) {
				if (rs.getBoolean("exists")) {
					User user = new User();
					user.setIdUser(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setAge(rs.getInt("age"));
					listUsers.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listUsers;
	}

	@Override
	public void addUser(User user) {
		try (Connection connection = DBCPDataSource.getConnection();
				PreparedStatement pst = connection
						.prepareStatement("INSERT INTO users(username, age, exists) VALUES(?, ?, ?)");) {
			pst.setString(1, user.getUsername());
			pst.setInt(2, user.getAge());
			pst.setBoolean(3, true);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getUserById(User user) {

		try (Connection connection = DBCPDataSource.getConnection();
				Statement stmt = connection.createStatement();
				CallableStatement proc = connection.prepareCall("{ ? = call updateIvan() }")) {
			stmt.execute("CREATE OR REPLACE PROCEDURE updateIvan(theAge int) AS '" + " BEGIN "
					+ "    UPDATE users SET age = theAge WHERE name = 'ivan';" + "    COMMIT; "
					+ " END;' LANGUAGE plpgsql");

			proc.registerOutParameter(1, Types.OTHER);
			proc.execute();
			ResultSet results = (ResultSet) proc.getObject(1);
			while (results.next()) {
				// do something with the results...
			}
			results.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeUser(User user) {
		try (Connection connection = DBCPDataSource.getConnection();
				PreparedStatement pst = connection.prepareStatement("UPDATE users SET exists=false WHERE id = ?");) {
			pst.setInt(1, user.getIdUser());
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<User> sortUsers(String field, String howToChange) {
		List<User> listUsers = new ArrayList<>();

		String change = null;

		if (howToChange.equals("increase")) {
			change = "ASC";
		} else if (howToChange.equals("decrease")) {
			change = "DESC";
		}

		if (change != null) {
			try (Connection connection = DBCPDataSource.getConnection();
					Statement st = connection.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM USERS ORDER BY " + field + " " + change);) {
				while (rs.next()) {
					if (rs.getBoolean("exists")) {
						User user = new User();
						user.setIdUser(rs.getInt("id"));
						user.setUsername(rs.getString("username"));
						user.setAge(rs.getInt("age"));
						listUsers.add(user);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listUsers;

	}

}
