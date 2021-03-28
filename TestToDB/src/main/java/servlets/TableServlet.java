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

	public static void main(String[] args) {
		FactoryDB factory = new FactoryDBsql();
		ConnectorDB connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
		List<User> listUsers = connector.getUsers();
		System.out.println(listUsers != null);
		listUsers.forEach(x -> System.out.println(x));
	}
	private final String REQUEST_PARAMETR_ACTION = "action";
	private final String PAGE_ADD = "add";
	private final String PAGE_HOME = "home";
	private final String PAGE_REMOVE = "remove";

	private final Map<String, Action> actionMap = new HashMap<>();

	@Override
	public void init() throws ServletException {
		actionMap.put(PAGE_ADD, new AddAction());
		actionMap.put(PAGE_HOME, new HomeAction());
		actionMap.put(PAGE_REMOVE, new RemoveAction());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		final String actionKey = (String) request.getAttribute("home");
		final String actionKey = "home";
		actionMap.get(actionKey).execute(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final String actionKey = request.getParameter(REQUEST_PARAMETR_ACTION);
		actionMap.get(actionKey).execute(request, response);
	}

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
