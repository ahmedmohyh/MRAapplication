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
	public ArrayList<Movie> get_MovieOverview();
	public ArrayList<Movie> getUserMoviesForRating();
	public boolean rateMovie(Rating rate);
	public boolean saveFilmData(Movie film);
	//TODO add your function that should be coming from the database
}
