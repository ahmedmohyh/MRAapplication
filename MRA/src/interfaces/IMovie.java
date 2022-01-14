package interfaces;
import java.util.ArrayList;

import dbadapter.Movie;
import dbadapter.Rating;

/**
 * Interface for DBFacade to provide all necessary database function.
 * 
 * @author Ahmed Mosua
 *
 */
public interface IMovie {
	public ArrayList<Movie> getUserMovies();
	public ArrayList<Movie> getUserMoviesForRating();
	public boolean insertRating(Rating rate);
	
	
	//TODO add your function that should be coming from the database
}
