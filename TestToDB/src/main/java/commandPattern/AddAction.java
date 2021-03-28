package commandPattern;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AddingPageService;
import services.PageService;

public class AddAction implements Action {

	private final PageService service = new AddingPageService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.createPage(request, response);

	}

}
