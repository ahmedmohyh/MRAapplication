package testing;


import application.MRAapplication;
import dbadapter.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TESTDbFcade extends TestCase {
    //test objects get added before running the tests (required for inserRating test)
    // "notAdded objects get added during the other tests"

    private Movie testMovie = new Movie();
    private UserData testUserData = new UserData();
    private Movie notAddedMovie = new Movie();
    private UserData notAddedUserData = new UserData();
    private Rating testRating = new Rating();
    public TESTDbFcade() {
        super();
    }

    @Before
    public void setUp() {

        //  object to be tested
        testMovie.setActors("james caan");
        testMovie.setDirector("francis coppola");
        testMovie.setTitle("God Father");
        testMovie.setOriginalPublishingDate("1972-05-02 12:20:00");

        notAddedMovie.setActors("test actor");
        notAddedMovie.setDirector("test director");
        notAddedMovie.setTitle("test name");
        notAddedMovie.setOriginalPublishingDate("1972-05-02 12:20:00");

        testUserData.set_username("group 16");
        testUserData.set_age(25);
        testUserData.set_email("test_email@gmail");

        notAddedUserData.set_username("testUserName");
        notAddedUserData.set_age(25);
        notAddedUserData.set_email("test email");

        testRating.set_rating(4);
        testRating.set_comment("test comment");
        testRating.set_filmID(1);
        testRating.set_username(testUserData.get_username());

        // SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS Movie,Rating,UserData";
        String createMraDB = "CREATE DATABASE mra";

        String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword()
                + "&useUnicode=true&characterEncoding=UTF-8"
                + "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";

        String sqlInsertMovieToDataBaseMRA = "INSERT INTO movie (OriginalPublishingDate, title, director, Actorlist) values (?,?,?,?)";
        String sqlInsertUserDataToDataBaseMRA = "INSERT INTO userdata (username, age, email) values (?,?,?)";
        String sqlConnect ="use mra;";



        // Perform database updates
        try (Connection connection = DriverManager
                .getConnection(url)) {

            try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
                psClean.executeUpdate();
            }
            try (PreparedStatement psCreate = connection.prepareStatement(createMraDB)) {
               // psCreate.executeUpdate();
            }
            try (PreparedStatement psCreateDataBaseMra = connection.prepareStatement(sqlConnect)) {
                psCreateDataBaseMra.executeUpdate();
            }
            try {
                Connection connectionScript = DriverManager.getConnection(url);
                ScriptRunner runner = new ScriptRunner(connectionScript, false, false);
                String file = "C:/Users/osama/OneDrive/Desktop/New folder (3)/MRAapplication/SQL/SQLForMra.sql";
                runner.runScript(new BufferedReader(new FileReader(file)));
            } catch (SQLException e) {
                System.err.println("Unable to connect to server: " + e);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertMovieToDataBaseMRA)) {
                psInsertMovie.setString(1, testMovie.getOriginalPublishingDate());
                psInsertMovie.setString(2, testMovie.getTitle());
                psInsertMovie.setString(3, testMovie.getTitle());
                psInsertMovie.setString(4, testMovie.getActors());
                psInsertMovie.executeUpdate();
            } try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertUserDataToDataBaseMRA)) {
                psInsertMovie.setString(1, testUserData.get_username());
                psInsertMovie.setInt(2, testUserData.get_age());
                psInsertMovie.setString(3, testUserData.get_email());
                psInsertMovie.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOverView() {
        //DBFacade stub = mock(DBFacade.class);
        //DBFacade.setInstance(stub);
        List<Movie> movieList = DBFacade.getInstance().getUserMovies();
        assertEquals(1,movieList.size());
        assertEquals(testMovie.getOriginalPublishingDate(),movieList.get(0).getOriginalPublishingDate());
        System.out.println("TEST");
    }

    @Test
    public void testInsertFilm(){
        assertEquals(true, DBFacade.getInstance().insertFilm(notAddedMovie));

    }

    @Test
    public  void testInserUserData(){

        assertEquals(true, DBFacade.getInstance().insertUserData(notAddedUserData));
    }

    @Test
    public void testInsertRating(){
//        DBFacade.getInstance().insertFilm(notAddedMovie);
//        DBFacade.getInstance().insertUserData(testUserData);
        System.out.println(testRating.get_filmID());
        System.out.println(testRating.get_username());
        assertEquals(true, DBFacade.getInstance().insertRating(testRating));
    }


    @After
    public void tearDown() {

        // SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS Movie,Rating,UserData";
        String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword()
                + "&useUnicode=true&characterEncoding=UTF-8"
                + "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";

        // Perform database updates
        try (Connection connection = DriverManager
                .getConnection(url)) {

            try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
              //  psClean.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
