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

	}

}
