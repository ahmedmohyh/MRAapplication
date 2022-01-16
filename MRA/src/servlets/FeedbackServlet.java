package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRAapplication;

/**
 * Internal Servlet to print a nice form of 404 (webpage not found) error
 * 
 * @author swe.uni-due.de
 *
 */
public class FeedbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String message;
	private String pageTitle;
	private boolean value;
	
	public FeedbackServlet(String msg, String title, boolean value) {
		this.message = msg;
		this.pageTitle = title;
		this.value = value;
	}
	
	public FeedbackServlet() {
		
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		
		request.setAttribute("pagetitle", pageTitle);
		request.setAttribute("value", value);
		request.setAttribute("LoggedUser", MRAapplication.getInstance().getLoggedUserName());
		try {
			request.setAttribute("message", message);
			request.getRequestDispatcher("/templates/ResultFeedback.ftl").forward(
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