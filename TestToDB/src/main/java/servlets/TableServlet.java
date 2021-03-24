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

public class TableServlet extends HttpServlet {
	
	public static void main(String[] args) {
		FactoryDB factory = new FactoryDBsql();
		ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
		List<User> listUsers = connector.getUsers();
		System.out.println(listUsers!=null);
		listUsers.stream().forEach(x -> System.out.println(x));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuilder responseTemplate = new StringBuilder(
				"<html>\n" + "<body>\n" + "<h2>Hello Servlet</h2>\n");
		responseTemplate.append("<table border=\"1\">\r\n");

		FactoryDB factory = new FactoryDBsql();
		ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
		List<User> listUsers = connector.getUsers();
		if (listUsers != null) {
			listUsers.stream().forEach(x -> {
				responseTemplate.append("  <tr>\r\n" + "<td>" + x.getIdUser() + "</td>" + "<td>" + x.getUsername() + "</td>"
						+ "<td>" + x.getAge() + "</td>" + "  </tr>\r\n");
			});
		}
		responseTemplate.append("</table>");
		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = new StringBuilder(
				"<html>\n" + "<body>\n" + "<h2>Hello Servlet</h2>\n");
		responseTemplate.append("<table border=\"1\">");

		FactoryDB factory = new FactoryDBsql();
		ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
		List<User> listUsers = connector.getUsers();
		if (listUsers != null) {
			listUsers.stream().forEach(x -> {
				responseTemplate.append("  <tr>\r\n" + "<td>" + x.getIdUser() + "</td>" + "<td>" + x.getUsername() + "</td>"
						+ "<td>" + x.getAge() + "</td>" + "  </tr>\r\n");
			});
		}
		responseTemplate.append("</table>");
		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());
	}

}
