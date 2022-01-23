package testing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import application.MRAapplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;

import dbadapter.DBFacade;
import dbadapter.Movie;
import dbadapter.Rating;
import dbadapter.UserData;
import junit.framework.TestCase;

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
	
	@Test
	public void testByDefault() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);
	}
	
	@Test
	public void testGetUserMovies() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().getUserMovies();

		verify(stub, times(1)).getUserMovies();
	}
	
	
	@Test
	public void testGetUserMoviesForRating() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().getUserMoviesForRating();

		verify(stub, times(1)).getUserMoviesForRating();
	}
	
	@Test
	public void testInsertUserData() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().insertUserData(userData);

		verify(stub, times(1)).insertUserData(userData);
	}

	@Test
	public void testInsertFilm() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().insertFilm(movie);

		verify(stub, times(1)).insertFilm(movie);
	}
	
	@Test
	public void testInsertRating() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);

		MRAapplication.getInstance().insertRating(rating);

		verify(stub, times(1)).insertRating(rating);
	}

}
