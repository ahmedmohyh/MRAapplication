package application;

import java.util.ArrayList;
import dbadapter.DBFacade;
import dbadapter.Movie;
import dbadapter.Rating;
import dbadapter.UserData;
import interfaces.UCmds;

/**
 * This class contains the MRA  which acts as the interface between all components.
 * 
 * @author Ahmed Mousa
 *
 */
public class MRAapplication implements UCmds {

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

	/**
	 * Calling the get_get_MovieOverview() from the DB facade.
	 *
	 * @autor Ahmed Mousa
	 * @return List of Movies to be shown in the Movie Page
	 */
	@Override
	public ArrayList<Movie> forwardSeeMovieOverview() {
		return DBFacade.getInstance().get_MovieOverview();
	 
	}

	/**
	 * Calling the getUserMoviesForRating() from the DB facade.
	 *
	 * @autor Ahmed Mohamed
	 * @return List of Movies to be shown in the Drop down list in Rating Page
	 */
	@Override
	public ArrayList<Movie> getUserMoviesForRating() {

		return DBFacade.getInstance().getUserMoviesForRating();
	}

	/**
	 * Calling the rateMovie() from the DB facade.
	 *
	 * @autor Ahmed Mohamed
	 * @return boolean to know if the insertion process succeeded or not
	 */
	@Override
	public boolean forwardRateMovie(Rating rate) {
		
			return DBFacade.getInstance().rateMovie(rate);
	}

	/**
	 * Calling the saveFilmData() from the DB facade.
	 *
	 * @autor Osama Elsafty, Kentrand
	 * @return boolean to know if the saving Film process succeeded or not
	 */
	@Override
	public boolean AddNewFilm(Movie film) {
		
			return DBFacade.getInstance().saveFilmData(film);
	}

	/**
	 * Calling the saveUser() from the DB facade.
	 *
	 * @autor  Kentrand
	 * @return boolean to know if the saving User process succeeded or not
	 */
	@Override
	public boolean forwardRegisterRequest(UserData ud) {
			return DBFacade.getInstance().saveUser(ud);
	}

	/**
	 * this function checks each time we load any page that we have a registered user in our DB.
	 *
	 * @autor  Kentrand
	 * @return string of the recently registered user otherwise returns No User Registered as a string
	 */
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
