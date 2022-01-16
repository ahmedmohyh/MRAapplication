package interfaces;
import dbadapter.UserData;

/**
 * Interface for DBFacade to provide all necessary database function.
 * 
 * @author Ahmed Mosua
 *
 */
public interface IUser {
	
	public boolean insertUserData(UserData ud);
	//TODO add your function that should be coming from the database
}