package services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageService {
	
	void createPage(HttpServletRequest request, HttpServletResponse response)throws IOException;

}
