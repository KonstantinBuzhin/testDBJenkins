package commandPattern;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.HomePageService;
import services.PageService;

public class HomeAction implements Action {

	private final PageService service = new HomePageService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.createPage(request, response);
	}

}
