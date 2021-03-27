package commandPattern;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBsql;
import factoryDB.PostgreSQLConnectorDB;
import model.User;

public class AddAction implements Action {

	private FactoryDB factory;
	private ConnectorDB connector;
	public String title = "Adding page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = getHeaderPage(title);
		factory = new FactoryDBsql();
		connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
		if (request.getParameter("username") != null && request.getParameter("age") != null) {
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setAge(Integer.valueOf(request.getParameter("age")));
			connector.addUser(user);
			responseTemplate.append("<h4>User is added</h>");
		} else {
			responseTemplate.append("<form action=\"\" method=\"post\">");
			responseTemplate.append(
					"<label>Enter name</label><input placeholder=\"Enter name\" name=\"username\" required /><br>");
			responseTemplate
					.append("<label>Enter age</label> <input placeholder=\"Enter age \" name=\"age\" required /><br>");
			responseTemplate.append("<input type=\"submit\" value=\"Add user\" />");
			responseTemplate.append("</form> ");
		}

		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());

	}

}
