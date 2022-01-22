package testing;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;

/**
 * This class performs a system test on the GuestGUI using JWebUnit.
 * 
 * @author swe.uni-due.de
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
	public void testBrowseHolidayOffers() {
		// Start testing for guestgui
		tester.beginAt("guestgui");

		// Check all components of the search form
		tester.assertTitleEquals("VacationRental - Search Offers");
		tester.assertFormPresent();
		tester.assertTextPresent("Required Information");
		tester.assertTextPresent("Arrival Time");
		tester.assertFormElementPresent("arrivalTime");
		tester.assertTextPresent("Departure Time");
		tester.assertFormElementPresent("departureTime");
		tester.assertTextPresent("Persons");
		tester.assertFormElementPresent("persons");
		tester.assertButtonPresent("SelectHOWebpage");

		// Submit the form with given parameters
		tester.setTextField("arrivalTime", "06/23/2016");
		tester.setTextField("departureTime", "06/24/2016");
		tester.setTextField("persons", "2");

		tester.clickButton("SelectHOWebpage");

		// Check the representation of the table for an empty result
		tester.assertTablePresent("availableHOs");
		String[][] tableHeadings = { { "#", "Street", "Town", "Capacity" } };
		tester.assertTableEquals("availableHOs", tableHeadings);
	
	}

}
