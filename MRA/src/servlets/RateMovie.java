package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRAapplication;

import dbadapter.Movie;
import dbadapter.Rating;

public class RateMovie extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	       if (MRAapplication.getInstance().getLoggedUserName().equals("No Loggedin user"))
	       {
	    	   FeedbackServlet error = new FeedbackServlet("there is no logged in user go to Registeration first","Error", false);
				error.doGet(request, response);
	       }
	       else {
	    	   
	      
		MRAapplication mrApp =  MRAapplication.getInstance();
		ArrayList<Movie> listOfMovies = mrApp.getUserMoviesForRating();
		// Dispatch request to template engine
        request.setAttribute("pagetitle", "Rate the Movie");
        request.setAttribute("LoggedUser", MRAapplication.getInstance().getLoggedUserName());
		request.setAttribute("listOfMovies", listOfMovies);
		try {
			request.getRequestDispatcher("/templates/RateMovie.ftl").forward(request, response);
		} catch (ServletException | IOException e) {
			request.setAttribute("errormessage",
					"Template error: please contact the administrator");
			e.printStackTrace();
		}
	       }
	}

	/**
	 * Call doGet instead of doPost
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		MRAapplication mrApp =  MRAapplication.getInstance();
		Rating newRate = new Rating();
		String userName = mrApp.getLoggedUserName();
		
		if (userName == null || userName.equals("No Loggedin user")) {
			FeedbackServlet error = new FeedbackServlet("there is no logged in user ","Error", false);
			error.doGet(request, response);
		}
		else {
		newRate.set_filmID(Integer.parseInt(request.getParameter("movieID")));
		newRate.set_rating(Integer.parseInt(request.getParameter("ratingNumber")));
		newRate.set_comment(request.getParameter("comment"));
		newRate.set_username(userName);
		
		if(newRate.get_rating() > 10 || newRate.get_rating() < 0) {
			String msg = "Rating error: Rating number must be between 0 and 10.";
			String title = "Rating Error";
			FeedbackServlet error = new FeedbackServlet(msg,title, false);
			error.doGet(request, response);
		}else {
			if(mrApp.forwardRateMovie(newRate)) {
				String msg = "Rating successfully: Your rating has been added successfully.";
				String title = "Rated Sucessfully!";
				FeedbackServlet error = new FeedbackServlet(msg,title, true);
				error.doGet(request, response);	
			}else {
				String msg = "Rating error: You can't rate same move twice.";
				String title = "Rating Error";
				FeedbackServlet feedback = new FeedbackServlet(msg,title, false);
				feedback.doGet(request, response);	
			}
		}
	}
	} 
	

}
