package application;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import dbadapter.DBFacade;
import interfaces.GCmds;
import interfaces.SMCmds;

/**
 * This class contains the VRApplication which acts as the interface between all
 * components.
 * 
 * @author swe.uni-due.de
 *
 */
public class MRAapplication implements GCmds, SMCmds {

	private static MRAapplication instance;

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static MRAapplication getInstance() {
		if (instance == null) {
			instance = new MRAapplication();
		}

		return instance;
	}
}
