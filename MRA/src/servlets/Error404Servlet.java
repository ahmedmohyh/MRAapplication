package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Internal Servlet to print a nice form of 404 (webpage not found) error
 * 
 * @author swe.uni-due.de
 *
 */
public class Error404Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String message;
	private String pageTitle;
	private boolean value;
	
	public Error404Servlet(String msg, String title, boolean value) {
		this.message = msg;
		this.pageTitle = title;
		this.value = value;
	}
	
	public Error404Servlet() {
		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("pagetitle", pageTitle);
		request.setAttribute("value", value);
		try {
			request.setAttribute("message", message);
			request.getRequestDispatcher("/templates/error.ftl").forward(
					request, response);
		} catch (ServletException | IOException e) {
			request.setAttribute("errormessage",
					"Template error: please contact the administrator");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		doGet(request, response);
	}
}