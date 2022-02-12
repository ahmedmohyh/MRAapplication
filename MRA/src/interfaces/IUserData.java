package interfaces;
import dbadapter.UserData;

/**
 * Interface for DBFacade to provide all necessary database function.
 * 
 * @author Ahmed Mosua
 *
 */
public interface IUserData {
	
	public boolean saveUser(UserData ud);
	//TODO add your function that should be coming from the database
}