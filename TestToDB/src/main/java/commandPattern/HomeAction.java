package commandPattern;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBsql;
import factoryDB.PostgreSQLConnectorDB;
import model.User;
import services.HomePageService;
import services.PageService;

public class HomeAction implements Action {

//	private FactoryDB factory;
//	private ConnectorDB connector;
//	public String title = "Home page";
	private final PageService service = new HomePageService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.createPage(request, response);
//		StringBuilder responseTemplate = getHeaderPage(title);
//		responseTemplate.append(getTableSort());
//		responseTemplate.append("<table border=\"1\">");
//		factory = new FactoryDBsql();
//		connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
//		if (request.getParameter("field") != null && request.getParameter("howToChange") != null) {
//			List<User> listUsers = connector.sortUsers(request.getParameter("field"),
//					request.getParameter("howToChange"));
//			listUsers.forEach(x -> {
//				responseTemplate.append("  <tr>\r\n" + "<td>" + x.getIdUser() + "</td>" + "<td>" + x.getUsername()
//						+ "</td>" + "<td>" + x.getAge() + "</td>" + "  </tr>\r\n");
//			});
//		} else {
//			List<User> listUsers = connector.getUsers();
//			listUsers.forEach(x -> {
//				responseTemplate.append("  <tr>\r\n" + "<td>" + x.getIdUser() + "</td>" + "<td>" + x.getUsername()
//						+ "</td>" + "<td>" + x.getAge() + "</td>" + "  </tr>\r\n");
//			});
//		}
//		responseTemplate.append("</table>");
//		responseTemplate.append("</body>\n" + "</html>");
//		response.getWriter().write(responseTemplate.toString());

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
		
		responseTemplate.append("<input type=\"hidden\" id=\"action\" name=\"action\" value=\"home\">");

		responseTemplate.append("<input type=\"submit\" value=\"Sort\" />");

		responseTemplate.append("</form> ");
		return responseTemplate;
	};

}
