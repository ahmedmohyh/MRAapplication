package testing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import application.MRAapplication;

import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import dbadapter.DBFacade;
import dbadapter.Movie;
import dbadapter.Rating;
import dbadapter.UserData;
import junit.framework.TestCase;

//this class contains system tests.
//A stub is made in every method.

@PrepareForTest(DBFacade.class)
public class MRAapplicationTest extends TestCase {

	private UserData userData;
	private Movie movie;
	private Rating rating;

	@Before
	public void setUp() throws Exception {
		userData = new UserData();
		userData.set_username("kentnard");
		userData.set_age(22);
		userData.set_email("Kentnard@gmail.com");
		
		movie = new Movie();
		movie.setOriginalPublishingDate("2022-01-23 00:00:00");
		movie.setTitle("Vice");
		movie.setDirector("Adam McKay");
		movie.setActors("Amy Adams");
		movie.setRating(10);
		
		rating = new Rating();
		rating.set_comment("Good");
		rating.set_filmID(1);
		rating.set_rating(10);
		rating.set_username("kentnard");
	}

	public MRAapplicationTest() {
		super();
	}

	//this method contains the default test
	@Test
	public void testByDefault() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);
	}

	//this method contains the test for the method getUserMovies() from the class MRAapplication
	@Test
	public void testGetUserMovies() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().forwardSeeMovieOverview();

		verify(stub, times(1)).get_MovieOverview();
	}

	//this method contains the test for the method getUserMoviesForRating() from the class MRAapplication
	@Test
	public void testGetUserMoviesForRating() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().getUserMoviesForRating();

		verify(stub, times(1)).getUserMoviesForRating();
	}

	//this method contains the test for the method insertUserData() from the class MRAapplication
	@Test
	public void testInsertUserData() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().forwardRegisterRequest(userData);

		verify(stub, times(1)).saveUser(userData);
	}

	//this method contains the test for the method insertFilm() from the class MRAapplication
	@Test
	public void testInsertFilm() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().AddNewFilm(movie);

		verify(stub, times(1)).saveFilmData(movie);
	}


	//this method contains the test for the method insertRating() from the class MRAapplication
	@Test
	public void testInsertRating() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().forwardRateMovie(rating);

		verify(stub, times(1)).rateMovie(rating);
	}

}
