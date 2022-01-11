package interfaces;

import java.util.ArrayList;

import dbadapter.Movie;

/**
 * Interface that provides all method to interact with a guest.
 * 
 * @author Ahmed Mousa
 *
 */
public interface Usermds {

	/**
	 * Function that provides all movies with avg rating and sorted DESC.
	 * 
	 * paramates: NON
	 * return Arraylist of all Movies
	 * @author Ahmed Mousa
	 *
	 */
	public ArrayList<Movie> getUserMovies();

	//TODO: Add All your functions that should be implemented in MRA!!,
}
