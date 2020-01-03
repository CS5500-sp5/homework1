package cs5500.hw1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class TestPerson {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareTo() {
		
		String className = TestPerson.class.getName();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		Logger LOGGER = Logger.getLogger(className);
		
		FileHandler handler;
		try {
			handler = new FileHandler("logs.txt");
		} catch (SecurityException | IOException e) {
			throw new RuntimeException("Woops.  Something burped on opening the log.");
		} 
		handler.setFormatter(new SimpleFormatter()); 

		LOGGER.addHandler(handler); 
		
		LOGGER.entering(className, method);
		
		Person p1 = new Person();
		p1.setName("Kanye");
		p1.setAttribute(Attributes.MALE);
		
		Person p2 = new Person();
		p2.setName("Kim");
		p2.setAttribute(Attributes.FEMALE);
		 
		LOGGER.log(Level.INFO, "Considering " + p1.getName() + " and " + p2.getName());

		assertThat(p1.compareTo(p2), lessThan(0));
		assertThat(p2.compareTo(p1), greaterThan(0));
		
		LOGGER.log(Level.INFO, "Done - passed.");
	}
	
	
}
