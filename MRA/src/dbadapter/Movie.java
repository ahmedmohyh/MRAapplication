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
	
	public Movie(int id, Timestamp pupDate, String title, String director, String actorList) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.OriginalPublishingDate = pupDate;
		this.title = title;
		this.direcotr = director;
		this.Acotrs = actorList;
	}
	//Functions Section
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getOriginalPublishingDate() {
		return OriginalPublishingDate;
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
	public String toString() {
		return "Movie " + id + " PubDate: " + this.OriginalPublishingDate +" director: " + this.direcotr
				+ " title: " + this.title + " Actors " + this.Acotrs;
	}
}
