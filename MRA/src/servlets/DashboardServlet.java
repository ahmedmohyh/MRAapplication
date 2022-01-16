package servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRAapplication;

public class DashboardServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet contains the insertOffer form
	 * @author Ahmed Mousa
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	       if (MRAapplication.getInstance().getLoggedUserName().equals("No Loggedin user"))
	       {
	    	   FeedbackServlet error = new FeedbackServlet("there is no logged in user go to Registeration first","Error", false);
				error.doGet(request, response);
	       }
	       else {
	       	       
	        request.setAttribute("pagetitle", "Welcome");
	        request.setAttribute("LoggedUser", MRAapplication.getInstance().getLoggedUserName());
	        try {
	        	 request.getRequestDispatcher("/templates/Dashboard.ftl").forward(request, response);
	        	 
			}
	        catch (ServletException | IOException e) {
				request.setAttribute("errormessage",
						"Template error: please contact the administrator");
				e.printStackTrace();
				
			}
	       }
	}

	/**
	 * Contains method when user interact with the frontend call
	 * @author Ahmed Mouas
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
