package application;

import java.util.ArrayList;
import dbadapter.DBFacade;
import dbadapter.Movie;
import dbadapter.Rating;
import dbadapter.UserData;
import interfaces.Usermds;

/**
 * This class contains the MRA  which acts as the interface between all components.
 * 
 * @author Ahmed Mousa
 *
 */
public class MRAapplication implements Usermds {

	private static MRAapplication instance;
	private String LoggedUserName = null;
	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static MRAapplication getInstance() {
		if (instance == null) {
			instance = new MRAapplication();
		}

		return instance;
	}

	@Override
	public ArrayList<Movie> getUserMovies() {
		return DBFacade.getInstance().getUserMovies();
	 
	}

	@Override
	public ArrayList<Movie> getUserMoviesForRating() {

		return DBFacade.getInstance().getUserMoviesForRating();
	}

	@Override
	public boolean insertRating(Rating rate) {
		
			return DBFacade.getInstance().insertRating(rate);
	}
	
	@Override
	public boolean insertFilm(Movie film) {
		
			return DBFacade.getInstance().insertFilm(film);
	}
	
	@Override
	public boolean insertUserData(UserData ud) {
			return DBFacade.getInstance().insertUserData(ud);
	}

	public String getLoggedUserName() {
		if (this.LoggedUserName == null)
		    return "No Loggedin user";
		else 
			return this.LoggedUserName;
	}

	public void setLoggedUserName(String loggedUserName) {
		LoggedUserName = loggedUserName;
	}
}
