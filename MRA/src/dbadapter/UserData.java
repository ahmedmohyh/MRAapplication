/**
	 * The UserData Class which describes our Model in the database 
	 * @autor Kentnard
	 */
package dbadapter;

public class UserData {
	//Attributes Section
	private int age;
	private String email;
	private String username;
	
	public UserData() {
		// TODO Auto-generated constructor stub
		this.username = "kentnard";
		this.age = 22;
		this.email = "kentnard@gmail.com";
	}
	
	//Functions Section

	public String get_username() {
		return username;
	}
	public void set_username(String username) {
		this.username = username;
	}
	public int get_age() {
		return age;
	}
	public void set_age(int age) {
		this.age = age;
	}
	public String get_email() {
		return email;
	}
	public void set_email(String email) {
		this.email = email;
	}
}