package testing;


import dbadapter.*;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TESTDbFcade extends TestCase {
    //test objects get added before running the tests (required for inserRating test)
    // "notAdded objects get added during the other tests"

    private Movie testMovie = new Movie();
    private UserData testUserData = new UserData();
    private Movie notAddedMovie = new Movie();
    private UserData notAddedUserData = new UserData();
    private Rating testRating = new Rating();
    private String Url = "jdbc:mysql://127.0.0.1:3306/mra?user=root&password=" + Configuration.getPassword()
            + "&useUnicode=true&characterEncoding=UTF-8"
            + "&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";
    public TESTDbFcade() {
        super();
    }

    /**
     * This function will be excuted every time we run a test in this class
     *
     * @author Ahmed Mohamed
     *
     */
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
                psInsertMovie.setString(3, testMovie.getDirector());
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

    /**
     * This unit test is to test the MovieOverview Page and its functionality
     *
     * @author Ahmed Mousa
     *
     */
    @Test
    public void testOverView()
    {
        List<Movie> movieList = DBFacade.getInstance().get_MovieOverview();
        assertEquals(1,movieList.size());
        assertEquals(testMovie.getOriginalPublishingDate(),movieList.get(0).getOriginalPublishingDate());
        assertEquals(testMovie.getTitle(),movieList.get(0).getTitle());
        assertEquals(testMovie.getDirector(),movieList.get(0).getDirector());
        assertEquals(testMovie.getActors(),movieList.get(0).getActors());

    }

    /**
     * This unit test is to test insert movie in the database and see that the database is actually changed.
     *
     * @author Ahmed Mousa
     *
     */
    @Test
    public void testInsertFilm(){
        DBFacade.getInstance().saveFilmData(notAddedMovie);
        List<Movie> movieList = DBFacade.getInstance().get_MovieOverview();
        assertEquals(2,movieList.size());
        assertEquals(notAddedMovie.getOriginalPublishingDate(),movieList.get(1).getOriginalPublishingDate());
        assertEquals(notAddedMovie.getTitle(),movieList.get(1).getTitle());
        assertEquals(notAddedMovie.getDirector(),movieList.get(1).getDirector());
        assertEquals(notAddedMovie.getActors(),movieList.get(1).getActors());

    }

    /**
     * This unit test is to test insert UserData in the database and see that the database is actually changed.
     *
     * @author Ahmed Mousa
     *
     */
    @Test
    public  void testInserUserData(){

         DBFacade.getInstance().saveUser(notAddedUserData);
        ArrayList<UserData> listUsers = new ArrayList<>();
        try (Connection connection = DriverManager
                .getConnection(this.Url)) {
            try (PreparedStatement psSelectUsers = connection.prepareStatement("select * from UserData where username = ?;")) {
                psSelectUsers.setString(1, notAddedUserData.get_username());
                ResultSet rs =   psSelectUsers.executeQuery();
                while(rs.next()){
                    listUsers.add(new UserData(rs.getInt(2),rs.getString(4),rs.getString(3)));
                }
            }catch (SQLException sqlE){
                sqlE.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(1,listUsers.size());
        assertEquals(notAddedUserData.get_age(),listUsers.get(0).get_age());
        assertEquals(notAddedUserData.get_username(),listUsers.get(0).get_username());
        assertEquals(notAddedUserData.get_email(),listUsers.get(0).get_email());

        }

    /**
     * This unit test is to test insert Rating in the database and see that the database is actually changed.
     *
     * @author Ahmed Mousa
     *
     */
    @Test
    public void testInsertRating(){
         DBFacade.getInstance().rateMovie(testRating);

        ArrayList<Rating> listRatings = new ArrayList<>();
        try (Connection connection = DriverManager
                .getConnection(this.Url)) {
            try (PreparedStatement psSelectUsers = connection.prepareStatement("select * from Rating where userName = ? and FilmID = ?;")) {
                psSelectUsers.setString(1, testRating.get_username());
                psSelectUsers.setInt(2, testRating.get_filmID());
                ResultSet rs =   psSelectUsers.executeQuery();
                while(rs.next()){
                    listRatings.add(new Rating(rs.getInt(2),rs.getString(5),rs.getInt(3),rs.getString(4)));
                }
            }catch (SQLException sqlE){
                sqlE.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(1,listRatings.size());
        assertEquals(testRating.get_rating(),listRatings.get(0).get_rating());
        assertEquals(testRating.get_username(),listRatings.get(0).get_username());
        assertEquals(testRating.get_filmID(),listRatings.get(0).get_filmID());
        assertEquals(testRating.get_comment(),listRatings.get(0).get_comment());

    }



}
