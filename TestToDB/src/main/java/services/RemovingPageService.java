package services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBsql;
import factoryDB.PostgreSQLConnectorDB;
import model.User;
import pages.RemovingPage;

public class RemovingPageService implements PageService {

	private FactoryDB factory;
	private ConnectorDB connector;
	private final String TITLE_PAGE = "Removing page";
	private final RemovingPage removingPage = new RemovingPage();

	@Override
	public void createPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = removingPage.getHeaderPage(TITLE_PAGE);

		if (request.getParameter("id") != null) {
			factory = new FactoryDBsql();
			connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
			User user = new User();
			user.setIdUser(Integer.valueOf(request.getParameter("id")));
			connector.removeUser(user);
			responseTemplate.append(removingPage.getSuccessfulLabel());
		} else {
			responseTemplate.append(removingPage.getFormRemoving());
		}
		responseTemplate.append(removingPage.getFooterPage());
		response.getWriter().write(responseTemplate.toString());

	}

}
