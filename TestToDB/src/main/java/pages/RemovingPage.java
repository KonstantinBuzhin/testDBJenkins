package pages;

public class RemovingPage implements Page {

	public StringBuilder getFormRemoving() {
		StringBuilder responseTemplate = new StringBuilder();
		responseTemplate.append("<form action=\"\" method=\"post\">");

		responseTemplate.append("<label>Enter id</label><input placeholder=\"Enter id\" name=\"id\" required /><br>");
		responseTemplate.append("<input type=\"hidden\" id=\"action\" name=\"action\" value=\"remove\">");
		responseTemplate.append("<input type=\"submit\" value=\"Remove user\" />");

		responseTemplate.append("</form> ");
		return responseTemplate;
	}

	public StringBuilder getSuccessfulLabel() {
		return new StringBuilder().append("<h4>User is removed</h>");
	}
}
