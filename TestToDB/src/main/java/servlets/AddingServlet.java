package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBsql;
import factoryDB.PostgreSQLConnectorDB;
import model.User;

public class AddingServlet extends HttpServlet {

//	public static void main(String[] args) {
//		FactoryDB factory = new FactoryDBsql();
//		ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
//		List<User> listUsers = connector.getUsers();
//		System.out.println(listUsers!=null);
//		listUsers.stream().forEach(x -> System.out.println(x));
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuilder responseTemplate = new StringBuilder("<html>\n"
				+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">"
				+ "<body>\n" + "<h2>Adding page</h2>\n");

		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/';\" value=\"Home\" />   ");
		responseTemplate
				.append("<input type=\"button\" onclick=\"location.href='/testToDB/adding';\" value=\"Adding\" />   ");
		responseTemplate.append(
				"<input type=\"button\" onclick=\"location.href='/testToDB/removing';\" value=\"Removing\" /><br><br>");

		responseTemplate.append("<form action=\"\" method=\"post\">");

		responseTemplate
				.append("<label>Enter name</label><input placeholder=\"Enter name\" name=\"username\" required /><br>");
		responseTemplate
				.append("<label>Enter age</label> <input placeholder=\"Enter age \" name=\"age\" required /><br>");
		responseTemplate.append("<input type=\"submit\" value=\"Add user\" />");

		responseTemplate.append("</form> ");

		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = new StringBuilder("<html>\n"
				+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">"
				+ "<body>\n" + "<h2>Adding page</h2>\n");

		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/';\" value=\"Home\" />   ");
		responseTemplate
				.append("<input type=\"button\" onclick=\"location.href='/testToDB/adding';\" value=\"Adding\" />   ");
		responseTemplate.append(
				"<input type=\"button\" onclick=\"location.href='/testToDB/removing';\" value=\"Removing\" /><br><br>");

		if (request.getParameter("username") != null && request.getParameter("age") != null) {
			FactoryDB factory = new FactoryDBsql();
			ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setAge(Integer.valueOf(request.getParameter("age")));
			connector.addUser(user);
			responseTemplate.append("<h4>User is added</h>");
		}

		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());
	}

}
