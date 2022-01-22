package testing;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;

/**
 * This class performs a system test on the GuestGUI using JWebUnit.
 * 
 * @author Kentnard
 *
 */
public class GuestGUIWebTestCase {

	private WebTester tester;
	/**
	 * Create a new WebTester object that performs the test.
	 */
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/MRA/");
	}

	
	@Test 
	public void testInsertUserData() { 
		// Start testing for registering, hence got to page of register 
		tester.beginAt("register_user");
	
		// Check all components of the search form
		tester.assertTitleEquals("MovieRatingApp - Registration");
		tester.assertFormPresent();
		tester.assertTextPresent("Register!");
		tester.assertTextPresent("Username");
		tester.assertFormElementPresent("username");
		tester.assertTextPresent("Email");
		tester.assertFormElementPresent("email");
		tester.assertTextPresent("Age");
		tester.assertFormElementPresent("age");
		// tester.assertSubmitButtonPresent(); // problematic
		tester.assertButtonPresent("registerButton");

		// Submit the form with given parameters
		tester.setTextField("username","maria");
		tester.setTextField("email", "maria@gmail.com");
		tester.setTextField("age", "18");
	 
		//tester.submit("registerButton"); // problematic
		tester.clickButton("registerButton");
	}
	
	
	
	@Test
	public void testInsertFilm() { 
		// Start testing for adding a movie
		tester.beginAt("add_movie");
	  
		// Check all components of the search form
		tester.assertTitleEquals("MovieRatingApp - Add Movie");
		tester.assertFormPresent();
		tester.assertTextPresent("Add Movie to the Database!");
		tester.assertTextPresent("Title"); tester.assertFormElementPresent("title");
		tester.assertTextPresent("Director");tester.assertFormElementPresent("director");
		tester.assertTextPresent("Original Publishing Date");
		tester.assertFormElementPresent("OriginalPublishingDate");
		tester.assertTextPresent("Main Actor List");
		tester.assertFormElementPresent("Actors"); 
		tester.assertButtonPresent("addMovieButton");
	  
		// Submit the form with given parameters
		tester.setTextField("title", "The Avengers");
		tester.setTextField("director", "Joss Whedon");
		tester.setTextField("OriginalPublishingDate", "05/01/2021 00:00:00");
		tester.setTextField("Actors", "Robert Downey Jr., Chris Evans");
	  
		tester.clickButton("addMovieButton"); 
	}
	
	 
	
	@Test
	public void testInsertRating() {
		// Start testing for rating a movie
		tester.beginAt("rate_movie");
	  
		// Check all components of the search form 
		tester.assertTitleEquals("MovieRatingApp - Rate the Movie");
		tester.assertFormPresent();
		tester.assertTextPresent("Rate the Movie!");
		tester.assertTextPresent("List of the Movies");
		tester.assertSelectOptionPresent("movieID", "List of the Movies");
		tester.assertTextPresent("Rating");
		tester.assertFormElementPresent("ratingNumber");
		tester.assertTextPresent("Comment");
		tester.assertFormElementPresent("comment");
		tester.assertButtonPresent("rateMovieButton");
	  
		// Submit the form with given parameters
		tester.selectOptionByValue("movieID", "1");
		tester.setTextField("ratingNumber","10");
		tester.setTextField("comment", "Love the movie very much!");
		tester.setHiddenField("username", "maria");
		
		tester.clickButton("rateMovieButton");
	}
	
	
	@Test
		public void testGetUserMovies() {
		// Start testing for rating a movie
		tester.beginAt("AllMovieOverView");
		
		// Check the representation of the table for an empty result
		tester.assertTablePresent("tblMovies");
		String[][] tableHeadings = { { "Title", "Director", "Publishing Date", "Actors", "Avg Rating" } };
		tester.assertTableRowsEqual("tblMovies", 0, tableHeadings);
	}
	
}
