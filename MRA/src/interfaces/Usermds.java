package interfaces;

import java.util.ArrayList;

import dbadapter.Movie;
import dbadapter.Rating;

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
	public ArrayList<Movie> getUserMoviesForRating();
	public boolean insertRating(Rating rate);

	//TODO: Add All your functions that should be implemented in MRA!!,
}
