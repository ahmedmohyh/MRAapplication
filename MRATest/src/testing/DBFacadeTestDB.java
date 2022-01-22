package testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;

import dbadapter.Configuration;
import dbadapter.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import junit.framework.TestCase;

/**
 * Testing our DBFacade.
 * 
 * @author swe.uni-due.de
 *
 */
public class DBFacadeTestDB extends TestCase {


	/**
	 * Preparing classes with static methods
	 */
	@Before
	public void setUp() {

		// Movie object to be tested
		Movie testMovie = new Movie();
		testMovie.setActors("james caan");
		testMovie.setDirector("francis coppola");
		testMovie.setTitle("God Father");
		testMovie.setOriginalPublishingDate("1972-05-02 12:20:00");

		// SQL statements
		String sqlCleanDB = "DROP DATABASE mra IF EXISTS";
		String createMraDB = "CREATE DATABASE mra";

		String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword()
				+ "&useUnicode=true&characterEncoding=UTF-8"
				+ "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";

		String sqlInsertMovieToDataBaseMRA = "INSERT INTO movie (OriginalPublishingDate, title, director, Actorlist) values (?,?,?,?)";

		try {
			Path sqlScript = Path.of("SQLForMra.sql");
			String sqlCreateDataBaseTables2 = Files.readString(sqlScript);
		}catch (IOException e){
			e.printStackTrace();
		}

		String sqlCreateDataBaseTables = "SET SQL_MODE = \"NO_AUTO_VALUE_ON_ZERO\";\n" +
				"SET AUTOCOMMIT = 0;\n" +
				"START TRANSACTION;\n" +
				"SET time_zone = \"+00:00\";\n" +
				"\n" +
				"CREATE TABLE `Movie` (\n" +
				"  `id` int(11) NOT NULL,\n" +
				"  `OriginalPublishingDate` timestamp NULL DEFAULT NULL,\n" +
				"  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,\n" +
				"  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,\n" +
				"  `ActorList` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
				"\n" +
				"ALTER TABLE `Movie`\n" +
				"  ADD PRIMARY KEY (`id`);\n" +
				"  \n" +
				"ALTER TABLE `Movie`\n" +
				"  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;\n" +
				"  \n" +
				"  CREATE TABLE `UserData` (\n" +
				"  `id` int(11) NOT NULL,\n" +
				"  `age` int(11) NOT NULL,\n" +
				"  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL UNIQUE,\n" +
				"  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL\t\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
				"\n" +
				"ALTER TABLE `UserData`\n" +
				"  ADD PRIMARY KEY (`id`);\n" +
				"  \n" +
				"ALTER TABLE `UserData`\n" +
				"  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;\n" +
				"\n" +
				"  CREATE TABLE `Rating` (\n" +
				"  `id` int(11) NOT NULL,\n" +
				"  `rating` int(11) NOT NULL,\n" +
				"  `FilmID` int(11) NOT NULL,\n" +
				"  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,\n" +
				"  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin\n" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
				"\n" +
				"ALTER TABLE `Rating`\n" +
				" ADD PRIMARY KEY (`id`);\n" +
				"  \n" +
				"ALTER TABLE `Rating`\n" +
				"  ADD foreign key (`FilmID`) references Movie(`id`);\n" +
				"  \n" +
				"ALTER TABLE `Rating`\n" +
				"  ADD foreign key (`userName`) references UserData(`userName`);\n" +
				"  \n" +
				"ALTER TABLE `Rating`\n" +
				"  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;\n" +
				"\n" +
				"ALTER TABLE `Rating`\n" +
				"    ADD CONSTRAINT `Rating` UNIQUE(`userName`, `FilmID`);\n" +
				"\n" +
				"  ALTER TABLE `Movie`\n" +
				"  ADD CONSTRAINT `Movie` UNIQUE(`title`, `OriginalPublishingDate`);\n" +
				"\n" +
				"\n" +
				"COMMIT;";


		// Perform database updates
		try (Connection connection = DriverManager
				.getConnection(url)) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
			try (PreparedStatement psCreate = connection.prepareStatement(createMraDB)) {
				psCreate.executeUpdate();
			}
			try (PreparedStatement psCreateDataBaseMra = connection.prepareStatement(sqlCreateDataBaseTables)) {
				psCreateDataBaseMra.executeUpdate();
			}
			try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertMovieToDataBaseMRA)) {
				psInsertMovie.setString(1, testMovie.getOriginalPublishingDate());
				psInsertMovie.setString(2, testMovie.getTitle());
				psInsertMovie.setString(3, testMovie.getTitle());
				psInsertMovie.setString(4, testMovie.getActors());
				psInsertMovie.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Testing getAvailableHolidayOffers with non-empty results.
	 */


	/**
	 * Testing getAvailableHolidayOffer with empty result.
	 */


	/**
	 * Rest database
	 */
	@After
	public void tearDown() {

		// SQL statements
		String sqlCleanDB = "DROP DATABASE mra IF EXISTS";
		String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword()
				+ "&useUnicode=true&characterEncoding=UTF-8"
				+ "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";

		// Perform database updates
		try (Connection connection = DriverManager
				.getConnection(url)) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
