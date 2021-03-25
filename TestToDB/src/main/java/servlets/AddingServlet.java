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

	private FactoryDB factory;
	private ConnectorDB connector;
	public String title = "Adding page";


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = getHeaderPage(title);

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
		StringBuilder responseTemplate = getHeaderPage(title);

		if (request.getParameter("username") != null && request.getParameter("age") != null) {
			factory = new FactoryDBsql();
			connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setAge(Integer.valueOf(request.getParameter("age")));
			connector.addUser(user);
			responseTemplate.append("<h4>User is added</h>");
		}

		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());
	}
	
	public StringBuilder getHeaderPage(String title) {
		StringBuilder responseTemplate = new StringBuilder("<html>\n"
				+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">"
				+ "<body>\n" + "<h2>"+title+"</h2>\n");
		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/';\" value=\"Home\" />   ");
		responseTemplate
				.append("<input type=\"button\" onclick=\"location.href='/testToDB/adding';\" value=\"Adding\" />   ");
		responseTemplate.append(
				"<input type=\"button\" onclick=\"location.href='/testToDB/removing';\" value=\"Removing\" /><br><br>");
		return responseTemplate;
	};

}
