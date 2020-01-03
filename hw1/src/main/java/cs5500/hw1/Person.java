package cs5500.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Person implements Comparable<Person> {
	
	public Person(String name, Attributes attribute, List<String> preferences) {
		setName(name);
		setAttribute(attribute);
		setPreferences(preferences);
	}
	
	public Person() {
		
	}

	public Attributes getAttribute() {
		return attribute;
	}
	
	public void setAttribute(Attributes attribute) {
		
		this.attribute = attribute;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public List<String> getPreferences() {
		
		return  preferenceList.stream().collect(Collectors.toList());
	}
	
	public void setPreferences(List<String> preferences) {
		
		preferenceList = new ArrayList<String>(preferences);
	}
	
	public Person getMatch() {
		return match;
	}
	
	public void setMatch(Person p) {
		
		this.match = p;
	}
	
	@Override
	public boolean equals(Object another) {
		
		if (this == another) {
			return true;
		}
		if (another == null) {
			return false;
		}
		if (!(another instanceof Person)) {
		    return false;
		}
		
		Person other = (Person) another;
		
		if (getName().contentEquals(other.getName())     &&
				(getAttribute() == other.getAttribute()) &&
				(getMatch() == other.getMatch())         &&
				(getPreferences().equals(other.getPreferences()))) {
			return true;
		}
		return false;
	}
	
	@Override
    public int compareTo(Person another) {

		return name.compareTo(another.getName());
    }
	
	private List<String> preferenceList;
	private Attributes attribute;
	String name;
	private Person match;
	
	
}
