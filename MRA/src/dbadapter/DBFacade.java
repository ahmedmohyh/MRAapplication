/**
 * The DBFacade Class which contains all our SQL statements which go directly with the database
 * All the methods have to first in the Interface IMovie and then override and implement 
 * @autor Ahmed Mousa.
 */

package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	private boolean complete = true;

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
		//String url = "jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":" + Configuration.getPort() + "/" + Configuration.getDatabase() + "?user=" + Configuration.getUser();
		String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword() 
		+ "&useUnicode=true&characterEncoding=UTF-8"
		+ "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";
		

		// Declare the necessary SQL queries.
		String sqlSelect = "select m.id, m.OriginalPublishingDate, m.title, m.director, m.ActorList, COALESCE(avg(r.rating),0) as rating from movie m left join rating r on m.id = r.filmID\r\n"
				+ "group by m.id\r\n"
				+ "order by avg(r.rating) desc";

		try (Connection connection = DriverManager.getConnection(url)) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Movie temp = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5),rs.getDouble(6));
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
	
	/**
	 * This function return a list of all movies so that they can be shown in a dropdown list
	 * @param None
	 * @return ArrayList of all Movies 
	 * @autor Kentnard
	 */
	@Override
	public ArrayList<Movie> getUserMoviesForRating() {
		ArrayList<Movie> result = new ArrayList<Movie>();

		// Declare the necessary SQL queries.
		//String url = "jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":" + Configuration.getPort() + "/" + Configuration.getDatabase() + "?user=" + Configuration.getUser();
		String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword() 
		+ "&useUnicode=true&characterEncoding=UTF-8"
		+ "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";
		try (Connection connection = DriverManager.getConnection(url)) {

			try (PreparedStatement ps = connection.prepareStatement("SELECT m.id, m.title from movie m group by m.id")) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Movie temp = new Movie();
						temp.setId(rs.getInt(1));
						temp.setTitle(rs.getString(2));
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

	@Override
	public boolean insertRating(Rating rate) {
		//String url = "jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":" + Configuration.getPort() + "/" + Configuration.getDatabase() + "?user=" + Configuration.getUser();
		String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword() 
		+ "&useUnicode=true&characterEncoding=UTF-8"
		+ "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";
		// Declare the necessary SQL queries.
		try (Connection connection = DriverManager.getConnection(url)) {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into Rating (rating, comment, filmID, username) values (?, ?, ?, ?)");
	        preparedStatement.setInt(1, rate.get_rating());
	        preparedStatement.setString(2, rate.get_comment());
			preparedStatement.setInt(3, rate.get_filmID());
	        preparedStatement.setString(4, rate.get_username());
	        preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * This function adds new film to the database, but it first checks whether the film was already in the database
	 * and if it was there it will return false and not add it
	 * @return true if the film is added successfully,otherwise false
	 * @autor Osama Elsafty
	 */
	@Override
	public boolean insertFilm(Movie film) {
		String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword() 
		+ "&useUnicode=true&characterEncoding=UTF-8"
		+ "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";
		
		String queryFilm = "SELECT * FROM movie WHERE title=? AND OriginalPublishingDate=?;";
		String insertFilmSql= "NSERT INTO movie (OriginalPublishingDate, title, director, Actorlist) values (?,?,?,?)";
		
		try (Connection connection = DriverManager.getConnection(url)) {
			// Checking if the Movie already exists 
			try (PreparedStatement ps = connection.prepareStatement(queryFilm)) {
				ps.setString(1, film.getTitle());
				ps.setString(2, film.getOriginalPublishingDate());
				ResultSet rs = ps.executeQuery();
				
				if(rs.next() == false){
					//Meaning that the film wasn't originally in the database
					try(PreparedStatement ps2 = connection.prepareStatement(insertFilmSql)) {
						ps2.setString(1, film.getOriginalPublishingDate());
						ps2.setString(2, film.getTitle());
						ps2.setString(3, film.getDirector());
						ps2.setString(4, film.getActors());
						ps2.executeUpdate();
					}
					catch (Exception e) {
						e.printStackTrace();
						System.out.println("hi i got catched1");
						return false;
					}	
				}
			}	
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("hi i got catched2");
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("hi i got catched3");
			return false;
		}
		return true;
	}
}
	