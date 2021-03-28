package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBsql;
import factoryDB.PostgreSQLConnectorDB;
import model.User;
import pages.HomePage;

public class HomePageService implements PageService {
	private FactoryDB factory;
	private ConnectorDB connector;
	private final String TITLE_PAGE = "Home page";
	private final HomePage homePage = new HomePage();

	@Override
	public void createPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = homePage.getHeaderPage(TITLE_PAGE);
		responseTemplate.append(homePage.getTableSort());
		factory = new FactoryDBsql();
		connector = factory.getConnectorDB(new PostgreSQLConnectorDB());
		if (request.getParameter("field") != null && request.getParameter("howToChange") != null) {
//			List<User> listUsers = connector.sortUsers(request.getParameter("field"),
//					request.getParameter("howToChange"));
			responseTemplate.append(homePage.getTableUsers(connector.sortUsers(request.getParameter("field"),
					request.getParameter("howToChange"))));
		} else {
//			List<User> listUsers = connector.getUsers();
			responseTemplate.append(homePage.getTableUsers(connector.getUsers()));
		}
		response.getWriter().write(responseTemplate.toString());
	}

}