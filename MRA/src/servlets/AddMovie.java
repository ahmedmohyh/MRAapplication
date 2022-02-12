package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRAapplication;
import dbadapter.Movie;

public class AddMovie extends HttpServlet {

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
	        request.setAttribute("pagetitle", "Add Movie");
	        request.setAttribute("LoggedUser", MRAapplication.getInstance().getLoggedUserName());
	        try {
	        	 request.getRequestDispatcher("/templates/AddMovie.ftl").forward(request, response);
	        	 System.out.println("hi i got after forwarding");
			} catch (ServletException | IOException e) {
				request.setAttribute("errormessage",
						"Template error: please contact the administrator");
				e.printStackTrace();
				 System.out.println("hi i got catched");
			}
	       }
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		MRAapplication mrApp =  MRAapplication.getInstance();
		Movie newMovie = new Movie();
         String userName = mrApp.getLoggedUserName();
		
		if (userName.equals("No Loggedin user")) {
			FeedbackServlet error = new FeedbackServlet("there is no logged in user ","Error", false);
			error.doGet(request, response);
		}
		else {
		newMovie.setTitle(request.getParameter("title"));
		newMovie.setDirector(request.getParameter("director"));
		newMovie.setOriginalPublishingDate(request.getParameter("OriginalPublishingDate"));
		newMovie.setActors(request.getParameter("Actors"));
		
		if(mrApp.AddNewFilm(newMovie)) {
			FeedbackServlet feedback = new FeedbackServlet("The film is added successfully","Success", true);
			feedback.doGet(request, response);
		}
		else {
			String msg = "Register error: Movie already existed in the database";
			FeedbackServlet error = new FeedbackServlet(msg,"error Adding",false);
			error.doGet(request, response);
		}
	}
	}
}