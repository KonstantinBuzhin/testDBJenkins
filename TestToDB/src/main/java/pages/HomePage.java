package pages;

import java.util.List;

import model.User;

public class HomePage implements Page {

	public StringBuilder getTableRowUser(User user) {
		StringBuilder responseTemplate = new StringBuilder();
		responseTemplate.append("  <tr>\r\n" + "<td>" + user.getIdUser() + "</td>" + "<td>" + user.getUsername()
		+ "</td>" + "<td>" + user.getAge() + "</td>" + "  </tr>\r\n");
		return responseTemplate;
	}
	
	public StringBuilder getTableUsers(List<User> listUsers) {
		StringBuilder responseTemplate = new StringBuilder();
		responseTemplate.append("<table border=\"1\">");
		listUsers.forEach(user -> {
			responseTemplate.append(getTableRowUser(user));
		});
		responseTemplate.append("</table>");
		return responseTemplate;
	}

	public StringBuilder getFormSort() {
		StringBuilder responseTemplate = new StringBuilder();
		responseTemplate.append("<form action=\"\" method=\"post\">");

		responseTemplate.append(
				"<input type=\"radio\" id=\"username\"\r\n" + "     name=\"field\" value=\"username\" required />\r\n"
						+ "    <label for=\"username\">Username</label>\r\n");

		responseTemplate.append("<input type=\"radio\" id=\"age\"\r\n"
				+ "     name=\"field\" value=\"age\" required />\r\n" + "    <label for=\"username\">Age</label>\r\n");

		responseTemplate.append("<br>");

		responseTemplate.append("<input type=\"radio\" id=\"increase\"\r\n"
				+ "     name=\"howToChange\" value=\"increase\" required />\r\n"
				+ "    <label for=\"username\">Increase</label>\r\n");

		responseTemplate.append("<input type=\"radio\" id=\"decrease\"\r\n"
				+ "     name=\"howToChange\" value=\"decrease\" required />\r\n"
				+ "    <label for=\"username\">Decrease</label>\r\n");

		responseTemplate.append("<input type=\"hidden\" id=\"action\" name=\"action\" value=\"home\">");

		responseTemplate.append("<input type=\"submit\" value=\"Sort\" />");

		responseTemplate.append("</form> ");
		return responseTemplate;
	};

}
