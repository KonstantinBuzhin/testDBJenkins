package services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factoryDB.ConnectorDB;
import factoryDB.FactoryDB;
import factoryDB.FactoryDBPostgres;
import model.User;
import pages.AddingPage;

public class AddingPageService implements PageService {
	
	private FactoryDB factory;
	private ConnectorDB connector;
	private final String TITLE_PAGE = "Adding page";
	private final AddingPage addingPage = new AddingPage();
	
	@Override
	public void createPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder responseTemplate = addingPage.getHeaderPage(TITLE_PAGE);
		factory = new FactoryDBPostgres();
		connector = factory.getConnectorDB();
		if (request.getParameter("username") != null && request.getParameter("age") != null) {
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setAge(Integer.valueOf(request.getParameter("age")));
			connector.addUser(user);
			responseTemplate.append(addingPage.getSuccessfulLabel());
		} else {
			responseTemplate.append(addingPage.getFormAdding());
		}

		responseTemplate.append(addingPage.getFooterPage());
		response.getWriter().write(responseTemplate.toString());
		
	}

}
