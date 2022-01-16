package servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DashboardServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * doGet contains the insertOffer form
	 * @author Ahmed Mousa
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		//Only for testing 
	        System.out.println("hi i got here");	       
	        request.setAttribute("pagetitle", "Welcome");
			
	        try {
	        	 request.getRequestDispatcher("/templates/Dashboard.ftl").forward(request, response);
	        	 System.out.println("hi i got after forwarding");
			} catch (ServletException | IOException e) {
				request.setAttribute("errormessage",
						"Template error: please contact the administrator");
				e.printStackTrace();
				 System.out.println("hi i got catched");
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
