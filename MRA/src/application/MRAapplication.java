package application;

import java.util.ArrayList;
import dbadapter.DBFacade;
import dbadapter.Movie;
import interfaces.Usermds;

/**
 * This class contains the MRA  which acts as the interface between all components.
 * 
 * @author Ahmed Mousa
 *
 */
public class MRAapplication implements Usermds {

	private static MRAapplication instance;

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
}
