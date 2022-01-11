/**
 * The DBFacade Class which contains all our SQL statments which go directly with the database
 * All the methods have to first in the Interface Imovie and then override and implement 
 * @autor Ahmed Mousa.
 */

package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import interfaces.IMovie;
//TODO:Implement your own methods that should interact directly with the database!

/**
 * Class which acts as the connector between application and database. Creates
 * Java objects from SQL returns. Exceptions thrown in this class will be
 * catched with a 500 error page.
 * 
 * @author Ahmed Mousa
 *
 */
public class DBFacade implements IMovie {
	private static DBFacade instance;

	/**
	 * Constructor which loads the corresponding driver for the chosen database type
	 */
	private DBFacade() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}

	public static void setInstance(DBFacade dbfacade) {
		instance = dbfacade;
	}

	/**
	 * This function return a list of all movies sorted by the avg rating.
	 * @param None
	 * @return ArrayList of all Movies 
	 * @autor Ahmed Mousa.
	 */
	@Override
	public ArrayList<Movie> getUserMovies() {
		// TODO Auto-generated method stub

		ArrayList<Movie> result = new ArrayList<Movie>();
		String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=253500&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM movie";

		try (Connection connection = DriverManager.getConnection(url)) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Movie temp = new Movie(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4),
								rs.getString(5));
						result.add(temp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
