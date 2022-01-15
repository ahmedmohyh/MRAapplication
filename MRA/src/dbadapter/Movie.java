	/**
	 * The Movie Class which describes our Model in the database 
	 * @autor Ahmed Mousa.
	 */

package dbadapter;

import java.sql.Timestamp;
public class Movie {
	//Attributes Section
	private int id;
	private Timestamp OriginalPublishingDate;
	private String title;
	private String direcotr;
	private String Acotrs;
	private double Rating; // unmapped attribute
	
	public Movie(int id, Timestamp pupDate, String title, String director, String actorList, double rating) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.OriginalPublishingDate = pupDate;
		this.title = title;
		this.direcotr = director;
		this.Acotrs = actorList;
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
	public void setOriginalPublishingDate(Timestamp originalPublishingDate) {
		OriginalPublishingDate = originalPublishingDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirecotr() {
		return direcotr;
	}
	public void setDirecotr(String direcotr) {
		this.direcotr = direcotr;
	}
	public String getAcotrs() {
		return Acotrs;
	}
	public void setAcotrs(String acotrs) {
		Acotrs = acotrs;
	}
	
	@Override
	public String toString() {
		return "Movie " + id + " PubDate: " + this.OriginalPublishingDate +" director: " + this.direcotr
				+ " title: " + this.title + " Actors " + this.Acotrs;
	}

	public double getRating() {
		return Rating;
	}

	public void setRating(double rating) {
		Rating = rating;
	}
}
