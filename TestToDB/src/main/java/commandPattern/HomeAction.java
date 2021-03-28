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

	private final PageService service = new HomePageService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.createPage(request, response);
	}

}
