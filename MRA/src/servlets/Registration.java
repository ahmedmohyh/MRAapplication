package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRAapplication;

import dbadapter.UserData;

public class Registration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//Only for testing 
		
		request.setAttribute("pagetitle", "Registration");
		 request.setAttribute("LoggedUser", MRAapplication.getInstance().getLoggedUserName());
		try {
			 request.getRequestDispatcher("/templates/Registration.ftl").forward(request, response);
			       
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
		MRAapplication mrApp =  MRAapplication.getInstance();
		UserData newUD = new UserData();
		
		newUD.set_username(request.getParameter("username"));
		newUD.set_email(request.getParameter("email"));
		newUD.set_age(Integer.parseInt(request.getParameter("age")));
		
		if(newUD.get_age() < 18) {
			String msg = "Register error: You must be at least 18 years old.";
			FeedbackServlet error = new FeedbackServlet(msg,"Age invalid",false);
			error.doGet(request, response);
		}else {
			if(mrApp.forwardRegisterRequest(newUD)) {
				mrApp.setLoggedUserName(newUD.get_username());
				FeedbackServlet feedback = new FeedbackServlet("The user is registerd successfully","Success", true);
				feedback.doGet(request, response);
			}else {
				String msg = "Register error: Username must be unique.";
				FeedbackServlet error = new FeedbackServlet(msg,"Error Adding",false);
				error.doGet(request, response);
			}
		}
	}
}
