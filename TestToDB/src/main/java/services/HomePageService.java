package services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBPostgres;
import pages.HomePage;

public class HomePageService implements PageService {
	private FactoryDB factory;
	private ConnectorDB connector;
	private final String TITLE_PAGE = "Home page";
	private final HomePage homePage = new HomePage();

	@Override
	public void createPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = homePage.getHeaderPage(TITLE_PAGE);
		responseTemplate.append(homePage.getFormSort());
		factory = new FactoryDBPostgres();
		connector = factory.getConnectorDB();
		if (request.getParameter("field") != null && request.getParameter("howToChange") != null) {
			responseTemplate.append(homePage.getTableUsers(
					connector.sortUsers(request.getParameter("field"), request.getParameter("howToChange"))));
		} else {
			responseTemplate.append(homePage.getTableUsers(connector.getUsers()));
		}
		responseTemplate.append(homePage.getFooterPage());
		response.getWriter().write(responseTemplate.toString());
	}

}
