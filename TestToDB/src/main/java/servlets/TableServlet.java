package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commandPattern.Action;
import commandPattern.AddAction;
import commandPattern.HomeAction;
import commandPattern.RemoveAction;
import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBsql;
import factoryDB.PostgreSQLConnectorDB;
import model.User;

public class TableServlet extends HttpServlet {

//	public static void main(String[] args) {
//		FactoryDB factory = new FactoryDBsql();
//		ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
//		List<User> listUsers = connector.getUsers();
//		System.out.println(listUsers != null);
//		listUsers.forEach(x -> System.out.println(x));
//	}

	private FactoryDB factory;
	private ConnectorDB connector;
	public String title = "Home page";
	
	private Map<String,Action> actionMap = new HashMap<String,Action>();
	
	

	@Override
	public void init() throws ServletException {
		actionMap.put("home", new HomeAction());
		actionMap.put("add", new AddAction());
		actionMap.put("remove", new RemoveAction());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String actionKey = "home";
		Action action = actionMap.get(actionKey);
        action.execute(request, response);
		
		
		
		
		

//		StringBuilder responseTemplate = getHeaderPage(title);
//		responseTemplate.append(getTableSort());
//		responseTemplate.append("<table border=\"1\">\r\n");
//
//		factory = new FactoryDBsql();
//		connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
//		List<User> listUsers = connector.getUsers();
//		if (listUsers != null) {
//			listUsers.forEach(x -> {
//				responseTemplate.append("  <tr>\r\n" + "<td>" + x.getIdUser() + "</td>" + "<td>" + x.getUsername()
//						+ "</td>" + "<td>" + x.getAge() + "</td>" + "  </tr>\r\n");
//			});
//		}
//		responseTemplate.append("</table>");
//		responseTemplate.append("</body>\n" + "</html>");
//		response.getWriter().write(responseTemplate.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = getHeaderPage(title);
		responseTemplate.append(getTableSort());
		responseTemplate.append("<table border=\"1\">");
		if (request.getParameter("field") != null && request.getParameter("howToChange") != null) {
			factory = new FactoryDBsql();
			connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
			List<User> listUsers = connector.sortUsers(request.getParameter("field"),
					request.getParameter("howToChange"));
			if (listUsers != null) {
				listUsers.forEach(x -> {
					responseTemplate.append("  <tr>\r\n" + "<td>" + x.getIdUser() + "</td>" + "<td>" + x.getUsername()
							+ "</td>" + "<td>" + x.getAge() + "</td>" + "  </tr>\r\n");
				});
			}
		} else {

		}

		responseTemplate.append("</table>");
		responseTemplate.append("</body>\n" + "</html>");
		response.getWriter().write(responseTemplate.toString());
	}

	public StringBuilder getHeaderPage(String title) {
		StringBuilder responseTemplate = new StringBuilder("<html>\n"
				+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">"
				+ "<body>\n" + "<h2>" + title + "</h2>\n");
		responseTemplate.append("<input type=\"button\" onclick=\"location.href='/testToDB/';\" value=\"Home\" />   ");
		responseTemplate
				.append("<input type=\"button\" onclick=\"location.href='/testToDB/adding';\" value=\"Adding\" />   ");
		responseTemplate.append(
				"<input type=\"button\" onclick=\"location.href='/testToDB/removing';\" value=\"Removing\" /><br><br>");
		return responseTemplate;
	};

	public StringBuilder getTableSort() {
		StringBuilder responseTemplate = new StringBuilder();
		responseTemplate.append("<form action=\"\" method=\"post\">");

		responseTemplate.append(
				"<input type=\"radio\" id=\"username\"\r\n" + "     name=\"field\" value=\"username\" required />\r\n"
						+ "    <label for=\"username\">Username</label>\r\n");

		responseTemplate.append("<input type=\"radio\" id=\"age\"\r\n"
				+ "     name=\"field\" value=\"age\" required />\r\n" + "    <label for=\"username\">Age</label>\r\n");

		responseTemplate.append("<br>");

		responseTemplate.append("<input type=\"radio\" id=\"increase\"\r\n"
				+ "     name=\"howToChange\" value=\"increase\" required />\r\n"
				+ "    <label for=\"username\">Increase</label>\r\n");

		responseTemplate.append("<input type=\"radio\" id=\"decrease\"\r\n"
				+ "     name=\"howToChange\" value=\"decrease\" required />\r\n"
				+ "    <label for=\"username\">Decrease</label>\r\n");

		responseTemplate.append("<input type=\"submit\" value=\"Sort\" />");

		responseTemplate.append("</form> ");
		return responseTemplate;
	};

}
