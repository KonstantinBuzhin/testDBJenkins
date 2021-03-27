package commandPattern;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBsql;
import factoryDB.PostgreSQLConnectorDB;
import model.User;

public class RemoveAction implements Action {

	private FactoryDB factory;
	private ConnectorDB connector;
	public String title = "Removing page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuilder responseTemplate = getHeaderPage(title);

		if (request.getParameter("id") != null) {
			factory = new FactoryDBsql();
			connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
			User user = new User();
			user.setIdUser(Integer.valueOf(request.getParameter("id")));
			connector.removeUser(user);
			responseTemplate.append("<h4>User is removed</h>");
		} else {
			responseTemplate.append("<form action=\"\" method=\"post\">");

			responseTemplate
					.append("<label>Enter id</label><input placeholder=\"Enter id\" name=\"id\" required /><br>");
			responseTemplate.append("<input type=\"submit\" value=\"Remove user\" />");

			responseTemplate.append("</form> ");
		}
		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());

	}

}
