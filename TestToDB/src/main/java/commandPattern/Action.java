package commandPattern;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	void execute(HttpServletRequest request, HttpServletResponse response)throws IOException;
	
	default StringBuilder getHeaderPage(String title) {
		StringBuilder responseTemplate = new StringBuilder("<html>\n"
				+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">"
				+ "<body>\n" + "<h2>" + title + "</h2>\n");
		responseTemplate.append("<form action=\"\" method=\"post\">"
				+ "<input type=\"hidden\" id=\"action\" name=\"action\" value=\"home\">"
				+ "<input type=\"submit\" value=\"Home\" /></form>   ");
		responseTemplate.append("<form action=\"\" method=\"post\">"
				+ "<input type=\"hidden\" id=\"action\" name=\"action\" value=\"add\">"
				+ "<input type=\"submit\" value=\"Adding\" /></form>   ");
		responseTemplate.append("<form action=\"\" method=\"post\">"
				+ "<input type=\"hidden\" id=\"action\" name=\"remove\" value=\"home\">"
				+ "<input type=\"submit\" value=\"Removing\" /></form>   ");
		return responseTemplate;
	};
}
