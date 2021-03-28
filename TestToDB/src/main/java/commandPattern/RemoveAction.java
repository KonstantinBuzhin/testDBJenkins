package commandPattern;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.PageService;
import services.RemovingPageService;

public class RemoveAction implements Action {

	private final PageService service = new RemovingPageService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.createPage(request, response);

//		StringBuilder responseTemplate = getHeaderPage(title);
//
//		if (request.getParameter("id") != null) {
//			factory = new FactoryDBsql();
//			connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
//			User user = new User();
//			user.setIdUser(Integer.valueOf(request.getParameter("id")));
//			connector.removeUser(user);
//			responseTemplate.append("<h4>User is removed</h>");
//		} else {
//			responseTemplate.append("<form action=\"\" method=\"post\">");
//
//			responseTemplate
//					.append("<label>Enter id</label><input placeholder=\"Enter id\" name=\"id\" required /><br>");
//			responseTemplate.append("<input type=\"submit\" value=\"Remove user\" />");
//
//			responseTemplate.append("</form> ");
//		}
//		responseTemplate.append("</body>\n" + "</html>");
//		response.getWriter().write(responseTemplate.toString());

	}

}
