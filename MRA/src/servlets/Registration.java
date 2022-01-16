package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRAapplication;
import dbadapter.DBFacade;
import dbadapter.Movie;
import dbadapter.Rating;
import dbadapter.UserData;

public class Registration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//Only for testing 
		System.out.println("hi i got here");
		MRAapplication mrApp = new MRAapplication();
		
		request.setAttribute("pagetitle", "Registration");
		request.setAttribute("navtype", "MovieQuery");
					
		try {
			 request.getRequestDispatcher("/templates/Registration.ftl").forward(request, response);
			       System.out.println("hi i got after forwarding");
		} catch (ServletException | IOException e) {
			request.setAttribute("errormessage", "Template error: please contact the administrator");
			e.printStackTrace();
		}
	}

	/**
	 * Call doGet instead of doPost
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		MRAapplication mrApp = new MRAapplication();
		UserData newUD = new UserData();
		
		newUD.set_username(request.getParameter("username"));
		newUD.set_email(request.getParameter("email"));
		newUD.set_age(Integer.parseInt(request.getParameter("age")));
		
		if(newUD.get_age() < 18) {
			String msg = "Register error: You must be at least 18 years old.";
			Error404Servlet error = new Error404Servlet(msg);
			error.doGet(request, response);
		}else {
			if(mrApp.insertUserData(newUD)) {
				doGet(request, response);
			}else {
				String msg = "Register error: Username must be unique.";
				Error404Servlet error = new Error404Servlet(msg);
				error.doGet(request, response);
			}
		}
	}
}
