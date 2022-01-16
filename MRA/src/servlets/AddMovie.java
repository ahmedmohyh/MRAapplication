package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRAapplication;
import dbadapter.DBFacade;
import dbadapter.Movie;
import dbadapter.Rating;
import dbadapter.UserData;
import java.sql.Timestamp;

public class AddMovie extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		//Only for testing 
	        System.out.println("hi i got here");	       
	        request.setAttribute("pagetitle", "Add Movie");
			request.setAttribute("navtype", "MovieQuery");
			
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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		MRAapplication mrApp = new MRAapplication();
		Movie newMovie = new Movie();
		
		newMovie.setTitle(request.getParameter("title"));
		newMovie.setDirector(request.getParameter("director"));
		newMovie.setOriginalPublishingDate(request.getParameter("OriginalPublishingDate"));
		newMovie.setActors(request.getParameter("Actors"));
		
		if(mrApp.insertFilm(newMovie)) {
			doGet(request, response);
		}
		else {
			String msg = "Register error: Movie already existed in the database";
			Error404Servlet error = new Error404Servlet(msg);
			error.doGet(request, response);
		}
	}
}