package testing;


import application.MRAapplication;
import dbadapter.Configuration;
import dbadapter.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dbadapter.DBFacade;
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
    private Movie testMovie = new Movie();
    public TESTDbFcade() {
        super();
    }

    @Before
    public void setUp() {

        // Movie object to be tested
        testMovie.setActors("james caan");
        testMovie.setDirector("francis coppola");
        testMovie.setTitle("God Father");
        testMovie.setOriginalPublishingDate("1972-05-02 12:20:00");

        // SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS Movie,Rating,UserData";
        String createMraDB = "CREATE DATABASE mra";

        String url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword()
                + "&useUnicode=true&characterEncoding=UTF-8"
                + "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";

        String sqlInsertMovieToDataBaseMRA = "INSERT INTO movie (OriginalPublishingDate, title, director, Actorlist) values (?,?,?,?)";
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
                String file = "D:/UDE/fünftes Semester/Software/Block12/MRAgitApp/MRAapplication/SQL/SQLForMra.sql";
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
    public void testAddMovie(){
        System.out.println("A7A ya OSAMA");
    }


    @Test
    public void testInsertRating(){

    }

    @Test
    public  void TestInserUserData(){

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
