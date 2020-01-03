package cs5500.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MatchMaker {

	public void makeMatches() {
				
		if (proposers.size() > proposees.size()) {
			System.out.println("Some proposer will not get matched.");
		}
		else if (proposers.size() < proposees.size()){
			System.out.println("Some proposees will not get matched.");
		}
		
		System.out.println("\nAnd away we go...");
		
		findMatches();
	}
	
	private void findMatches() {

		for (int i = 0; i < proposers.size(); i++) {
			
			Person proposer = proposers.get(i);
			
			Boolean notMatched = true;
			int index = 0;
			
			System.out.println("\nConsidering " + proposer.getName() + " as proposer.");
			while (notMatched && (index < proposees.size())) {
				
				String interest = proposer.getPreferences().get(index++);
				
				Person otherPerson = proposees.stream()
						  .filter(p -> p.getName().equals(interest))
						  .findAny()
						  .orElse(null);
				
				System.out.println("Considering " + otherPerson.getName() + " as proposee.");
				
				if (otherPerson.getMatch() == null) {
					otherPerson.setMatch(proposer);
					proposer.setMatch(otherPerson);
					System.out.println(proposer.getName() + " is now matched with " + otherPerson.getName() + ".\n");
					notMatched = false;
				}
				else {
					System.out.println("Unfortunately, " + otherPerson.getName() + " is already matched.");
				}
			}
			
			if (proposer.getMatch() == null) {
				System.out.println(proposer.getName() + " did not find a match.\n");
			}
		}
		
	}

	public void setUpGroups(List<Person> people, Attributes proposerType, Attributes proposeeType) {
		
		if (people.isEmpty()) {
			throw new IllegalArgumentException("Empty list of people.");
		}
		
		if (proposerType == proposeeType) {
			throw new IllegalArgumentException("Proposers and Proposees shouldn't be the same.");
		}
		
		for (int i = 0; i < people.size(); i++) {
			
			if (people.get(i).getAttribute() == proposerType) {
				proposers.add(people.get(i));
			}
			else if (people.get(i).getAttribute() == proposeeType) {
				proposees.add(people.get(i));
			}
			else {
				throw new IllegalArgumentException("List of candidates has more than two groups.");
			}
		}
	}
	
	public List<Person> getList(String proposal) {
		
		if (!proposal.contentEquals("proposers") && !proposal.contentEquals("proposees")) {
			throw new IllegalArgumentException("Must be proposers or proposees - not " + proposal);
		}
		
		if (proposal.contentEquals("proposers")) {
			return proposers;
		}
		else if (proposal.contentEquals("proposees")) {
			return proposees;
		}
		else
			return null;
	}
	
	private List<Person> proposers = new ArrayList<>();
	private List<Person> proposees = new ArrayList<>();
	
}
