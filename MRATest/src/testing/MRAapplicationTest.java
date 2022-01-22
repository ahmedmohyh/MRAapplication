package testing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import application.MRAapplication;
import org.junit.Test;

import dbadapter.DBFacade;
import junit.framework.TestCase;

public class MRAapplicationTest extends TestCase {
	
	public MRAapplicationTest() {
		super();
	}
	
	@Test
	public void testByDefault() {
		DBFacade stub = mock(DBFacade.class);
		DBFacade.setInstance(stub);
		System.out.println("TEST");
	}
}
