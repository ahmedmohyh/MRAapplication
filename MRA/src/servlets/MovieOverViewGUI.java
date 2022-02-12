package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRAapplication;
import dbadapter.Movie;

/**
 * Contains GUI for staffmember
 * 
 * @author Ahmed Mousa
 *
 */
public class MovieOverViewGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet contains the insertOffer form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
       if (MRAapplication.getInstance().getLoggedUserName().equals("No Loggedin user"))
       {
    	   FeedbackServlet error = new FeedbackServlet("there is no logged in user go to Registeration first","Error", false);
			error.doGet(request, response);
       }
		//Only for testing 
       else {
       System.out.println("hi i got here");
		MRAapplication mrApp =  MRAapplication.getInstance();
	       ArrayList<Movie> movieQuery = mrApp.forwardSeeMovieOverview();
	       
	        request.setAttribute("movies", movieQuery);
	        request.setAttribute("pagetitle", "Welcome");
	        request.setAttribute("LoggedUser", MRAapplication.getInstance().getLoggedUserName());
	        try {
	        	 request.getRequestDispatcher("/templates/MovieOverView.ftl").forward(request, response);
	        	 
			} catch (ServletException | IOException e) {
				request.setAttribute("errormessage",
						"Template error: please contact the administrator");
				e.printStackTrace();
				
			}
       }
	}

	/**
	 * Contains handling of MovieOverView call
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}
}