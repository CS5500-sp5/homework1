package cs5500.hw1;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMatch {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeMatches() {
		
		List<String> boysPreferences = Arrays.asList("Diane", "Carla", "Lilith", "Kelly");
		List<String> womensPreferences = Arrays.asList("Sam", "Frasier", "Coach", "Woody");
		
		Person p1 = new Person("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith", "Kelly"));
		Person p2 = new Person("Frasier", Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla", "Kelly"));
		Person p3 = new Person("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane", "Kelly"));
		Person p4 = new Person("Woody", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith", "Kelly"));
		
		Person p5 = new Person("Diane", Attributes.FEMALE, womensPreferences);
		Person p6 = new Person("Carla", Attributes.FEMALE, womensPreferences);
		Person p7 = new Person("Lilith", Attributes.FEMALE, womensPreferences);
		Person p8 = new Person("Kelly", Attributes.FEMALE, womensPreferences);
		
		List<Person> everyone = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
		
		MatchMaker m = new MatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		
		m.makeMatches();
		assertThat(p1.getMatch().getName(), is(p5.getName()));
		assertThat(p2.getMatch().getName(), is(p7.getName()));
		assertThat(p3.getMatch().getName(), is(p6.getName()));
		assertThat(p4.getMatch().getName(), is(p8.getName()));

	}

	@Test
	public void testMakeMatchesTooFewProposers() {
		
		List<String> womensPreferences = Arrays.asList("Sam", "Frasier", "Coach");
		
		Person p1 = new Person("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith"));
		Person p2 = new Person("Frasier", Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla"));
		Person p3 = new Person("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane"));

		Person p5 = new Person("Diane", Attributes.FEMALE, womensPreferences);
		Person p6 = new Person("Carla", Attributes.FEMALE, womensPreferences);
		Person p7 = new Person("Lilith", Attributes.FEMALE, womensPreferences);
		Person p8 = new Person("Kelly", Attributes.FEMALE, womensPreferences);
		
		List<Person> everyone = Arrays.asList(p1, p2, p3, p5, p6, p7, p8);
		
		MatchMaker m = new MatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		
		m.makeMatches();
		assertThat(p1.getMatch().getName(), is(p5.getName()));
		assertThat(p2.getMatch().getName(), is(p7.getName()));
		assertThat(p3.getMatch().getName(), is(p6.getName()));
		assertThat(p8.getMatch(), is(nullValue()));
		
	}
	
	@Test
	public void testMakeMatchesTooFewProposees() {
		
		List<String> womensPreferences = Arrays.asList("Sam", "Frasier", "Coach", "Woody");
		
		Person p1 = new Person("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith"));
		Person p2 = new Person("Frasier", Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla"));
		Person p3 = new Person("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane"));
		Person p4 = new Person("Woody", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith"));
		
		Person p5 = new Person("Diane", Attributes.FEMALE, womensPreferences);
		Person p6 = new Person("Carla", Attributes.FEMALE, womensPreferences);
		Person p7 = new Person("Lilith", Attributes.FEMALE, womensPreferences);
		
		
		List<Person> everyone = Arrays.asList(p1, p2, p3, p4, p5, p6, p7);
		
		MatchMaker m = new MatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		
		m.makeMatches();
		
		assertThat(p1.getMatch(), is(p5));
		assertThat(p2.getMatch(), is(p7));
		assertThat(p3.getMatch(), is(p6));
		assertThat(p4.getMatch(), is(nullValue()));
		
	}
	
	@Test
	public void testSetUpGroups() {

		List<String> womensPreferences = Arrays.asList("Sam", "Frasier", "Coach", "Woody");
		
		Person p1 = new Person("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith", "Kelly"));
		Person p2 = new Person("Frasier", Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla", "Kelly"));
		Person p3 = new Person("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane", "Kelly"));
		Person p4 = new Person("Woody", Attributes.MALE, Arrays.asList("Kelly", "Diane", "Carla", "Lilith"));
		

		Person p5 = new Person("Diane", Attributes.FEMALE, womensPreferences);
		Person p6 = new Person("Carla", Attributes.FEMALE, womensPreferences);
		Person p7 = new Person("Lilith", Attributes.FEMALE, womensPreferences);
		Person p8 = new Person("Kelly", Attributes.FEMALE, womensPreferences);
		
		List<Person> everyone = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
		
		MatchMaker m = new MatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		
		List<Person> answer1 = Arrays.asList(p1, p2, p3, p4);
		List<Person> answer2 = Arrays.asList(p5, p6, p7, p8);
		
		assertThat(m.getList("proposers"), IsIterableContainingInOrder.contains(answer1.toArray()));
		assertThat(m.getList("proposees"), IsIterableContainingInOrder.contains(answer2.toArray()));

	}

}
