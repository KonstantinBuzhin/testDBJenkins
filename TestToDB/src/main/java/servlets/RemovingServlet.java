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

public class RemovingServlet extends HttpServlet {
	
//	public static void main(String[] args) {
//		FactoryDB factory = new FactoryDBsql();
//		ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
//		List<User> listUsers = connector.getUsers();
//		System.out.println(listUsers!=null);
//		listUsers.stream().forEach(x -> System.out.println(x));
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuilder responseTemplate = new StringBuilder("<html>\n" + "<body>\n" + "<h2>Removing page</h2>\n");
		
		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/adding';\" value=\"Home\" />   ");
		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/adding';\" value=\"Adding\" />   ");
		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/removing';\" value=\"Removing\" /><br><br>");
		
		responseTemplate.append("<form action=\"\" method=\"post\">");

		responseTemplate.append(
				"<label>Enter id</label><input placeholder=\"Enter id\" name=\"id\" required /><br>");
		responseTemplate.append("<input type=\"submit\" value=\"Remove user\" />");

		responseTemplate.append("</form> ");

		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = new StringBuilder("<html>\n" + "<body>\n" + "<h2>Removing page</h2>\n");
		
		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/adding';\" value=\"Home\" />   ");
		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/adding';\" value=\"Adding\" />   ");
		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/removing';\" value=\"Removing\" /><br><br>");
		
		if (request.getParameter("id") != null) {
			FactoryDB factory = new FactoryDBsql();
			ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
			User user = new User();
			user.setIdUser(Integer.valueOf(request.getParameter("id")));
			connector.removeUser(user);
			responseTemplate.append("<h4>User is removed</h>");
		}

		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());
	}

}
