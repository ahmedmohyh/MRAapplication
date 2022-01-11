package timer;

import java.util.ArrayList;

import application.MRAapplication;
import dbadapter.Movie;

/**
 * Timer class to call the method checkPayment in the application. Main method
 * can be executed in a scheduled way.
 * 
 * @author Ahmed
 *
 */
public class Timer {
// i added this comment for testing purposes
	//new update
	public static void main(String[] args) {
		MRAapplication vrApp = new MRAapplication();
		ArrayList<Movie> test = vrApp.getUserMovies();
		
		for (Movie m : test) {
			System.out.print(m.toString());
		}
		//vrApp.checkPayment();
		System.out
				.println("All bookings not paid and older than 14 days are successfully deleted");
	}
}
