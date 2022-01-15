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

		//Only for testing 
		System.out.println("hi i got here");
		MRAapplication mrApp = new MRAapplication();
	       ArrayList<Movie> movieQuery = mrApp.getUserMovies();
	       
	        request.setAttribute("movies", movieQuery);
	        request.setAttribute("pagetitle", "Welcome");
			request.setAttribute("navtype", "MovieQuery");
			
	        try {
	        	 request.getRequestDispatcher("/templates/MovieOverView.ftl").forward(request, response);
	        	 System.out.println("hi i got after forwarding");
			} catch (ServletException | IOException e) {
				request.setAttribute("errormessage",
						"Template error: please contact the administrator");
				e.printStackTrace();
				 System.out.println("hi i got catched");
			}
	}

	/**
	 * Contains handling of MovieOverView call
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}
}