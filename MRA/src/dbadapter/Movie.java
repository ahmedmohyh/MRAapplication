	/**
	 * The Movie Class which describes our Model in the database 
	 * @autor Ahmed Mousa.
	 */

package dbadapter;

import java.sql.Timestamp;
public class Movie {
	//Attributes Section
	private int id;
	private String OriginalPublishingDate;
	private String title;
	private String director;
	private String Actors;
	private double Rating; // unmapped attribute
	
	public Movie(int id, String pupDate, String title, String director, String actorList, double rating) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.OriginalPublishingDate = pupDate;
		this.title = title;
		this.director = director;
		this.Actors = actorList;
		this.Rating = rating;
	}
	
	public Movie() {}
	
	//Functions Section
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginalPublishingDate() {
		return OriginalPublishingDate.toString();
	}
	public void setOriginalPublishingDate(String originalPublishingDate) {
		OriginalPublishingDate = originalPublishingDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActors() {
		return Actors;
	}
	public void setActors(String actors) {
		Actors = actors;
	}
	
	@Override
	public String toString() {
		return "Movie " + id + " PubDate: " + this.OriginalPublishingDate +" director: " + this.director
				+ " title: " + this.title + " Actors " + this.Actors;
	}

	public double getRating() {
		return Rating;
	}

	public void setRating(double rating) {
		Rating = rating;
	}
}
