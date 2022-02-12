package interfaces;

import java.util.ArrayList;

import dbadapter.Movie;
import dbadapter.Rating;
import dbadapter.UserData;

/**
 * Interface that provides all method to interact with a guest.
 * 
 * @author Ahmed Mousa
 *
 */
public interface UCmds {

	/**
	 * Function that provides all movies with avg rating and sorted DESC.
	 * 
	 * paramates: NON
	 * return Arraylist of all Movies
	 * @author Ahmed Mousa
	 *
	 */
	public ArrayList<Movie> forwardSeeMovieOverview();
	public ArrayList<Movie> getUserMoviesForRating();
	public boolean forwardRateMovie(Rating rate);
	public boolean AddNewFilm(Movie film);
	public boolean forwardRegisterRequest(UserData ud);
	//TODO: Add All your functions that should be implemented in MRA!!,
}