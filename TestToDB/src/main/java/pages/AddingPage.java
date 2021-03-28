package pages;

public class AddingPage implements Page{
	
	public StringBuilder getFormAdding() {
		StringBuilder responseTemplate = new StringBuilder();
		responseTemplate.append("<form action=\"\" method=\"post\">");
		responseTemplate.append(
				"<label>Enter name</label><input placeholder=\"Enter name\" name=\"username\" required /><br>");
		responseTemplate
				.append("<label>Enter age</label> <input placeholder=\"Enter age \" name=\"age\" required /><br>");
		responseTemplate.append("<input type=\"submit\" value=\"Add user\" />");
		responseTemplate.append("</form> ");
		return responseTemplate;
	}
	
	public StringBuilder getSuccessfulLabel() {
		return new StringBuilder().append("<h4>User is added</h>");
	}

}
