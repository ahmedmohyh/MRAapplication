package interfaces;
import java.util.ArrayList;

import dbadapter.Movie;
import dbadapter.Rating;
import dbadapter.UserData;

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
	public boolean insertFilm(Movie film);
	//TODO add your function that should be coming from the database
}
