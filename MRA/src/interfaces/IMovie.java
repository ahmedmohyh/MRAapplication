package interfaces;
import java.util.ArrayList;

import dbadapter.Movie;

/**
 * Interface for DBFacade to provide all necessary database function.
 * 
 * @author Ahmed Mosua
 *
 */
public interface IMovie {
	public ArrayList<Movie> getUserMovies();
	
	//TODO add your function that should be coming from the database
}
