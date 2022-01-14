/**
	 * The Rating Class which describes our Model in the database 
	 * @autor Kentnard
	 */
package dbadapter;

public class Rating {
	//Attributes Section
	private int rating;
	private String comment;
	private int filmID;
	private String username;
	
	public Rating(int rating, String comment, int filmID, String username) {
		// TODO Auto-generated constructor stub
		this.rating = rating;
		this.comment = comment;
		this.filmID = filmID;
		this.username = username;
	}
	public Rating() {}
	//Functions Section
	public int get_rating() {
		return rating;
	}
	public void set_rating(int rating) {
		this.rating = rating;
	}
	public String get_comment() {
		return comment;
	}
	public void set_comment(String comment) {
		this.comment = comment;
	}
	public int get_filmID() {
		return filmID;
	}
	public void set_filmID(int filmID) {
		this.filmID = filmID;
	}
	public String get_username() {
		return username;
	}
	public void set_username(String username) {
		this.username = username;
	
	}
}