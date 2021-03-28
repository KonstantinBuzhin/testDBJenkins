package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commandPattern.Action;
import commandPattern.AddAction;
import commandPattern.HomeAction;
import commandPattern.RemoveAction;

public class TableServlet extends HttpServlet {
	
	private final String REQUEST_PARAMETR_ACTION = "action";
	private final String PAGE_ADD = "add";
	private final String PAGE_HOME = "home";
	private final String PAGE_REMOVE = "remove";

	private final Map<String, Action> actionMap = new HashMap<>();

	@Override
	public void init() throws ServletException {
		actionMap.put(PAGE_ADD, new AddAction());
		actionMap.put(PAGE_HOME, new HomeAction());
		actionMap.put(PAGE_REMOVE, new RemoveAction());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		actionMap.get(PAGE_HOME).execute(request, response);
	}
	

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final String actionKey = (String) request.getAttribute(REQUEST_PARAMETR_ACTION);
		actionMap.get(actionKey).execute(request, response);
	}

}
