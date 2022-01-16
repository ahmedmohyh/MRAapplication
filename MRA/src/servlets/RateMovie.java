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
import dbadapter.UserData;

public class RateMovie extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		MRAapplication mrApp =  MRAapplication.getInstance();
		ArrayList<Movie> listOfMovies = mrApp.getUserMoviesForRating();
		// Dispatch request to template engine
        request.setAttribute("pagetitle", "Rate the Movie");
		request.setAttribute("listOfMovies", listOfMovies);
		try {
			request.getRequestDispatcher("/templates/RateMovie.ftl").forward(request, response);
		} catch (ServletException | IOException e) {
			request.setAttribute("errormessage",
					"Template error: please contact the administrator");
			e.printStackTrace();
		}
	}

	/**
	 * Call doGet instead of doPost
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		MRAapplication mrApp =  MRAapplication.getInstance();
		Rating newRate = new Rating();
		UserData user = new UserData();
		
		newRate.set_filmID(Integer.parseInt(request.getParameter("movieID")));
		newRate.set_rating(Integer.parseInt(request.getParameter("ratingNumber")));
		newRate.set_comment(request.getParameter("comment"));
		newRate.set_username(user.get_username());
		
		if(newRate.get_rating() > 10 || newRate.get_rating() < 0) {
			String msg = "Rating error: Rating number must be between 0 and 10.";
			String title = "Rating Error";
			Error404Servlet error = new Error404Servlet(msg,title, false);
			error.doGet(request, response);
		}else {
			if(mrApp.insertRating(newRate)) {
				String msg = "Rating successfully: Your rating has been added successfully.";
				String title = "Rated Sucessfully!";
				Error404Servlet error = new Error404Servlet(msg,title, true);
				error.doGet(request, response);	
			}else {
				String msg = "Rating error: You can't rate same move twice.";
				String title = "Rating Error";
				Error404Servlet error = new Error404Servlet(msg,title, false);
				error.doGet(request, response);	
			}
		}
	}
	

}
