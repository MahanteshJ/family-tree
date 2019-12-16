package com.geeks.challenge.family.tree.rules.rigistry;

import com.geeks.challenge.family.tree.Person;

public class Relation {
	
	private String name;
	private Person fromPerson;
	private Person toPerson;
	private ConditonalParams conditionalParams;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Person getFromPerson() {
		return fromPerson;
	}
	
	public void setFromPerson(Person fromPerson) {
		this.fromPerson = fromPerson;
	}
	
	public Person getToPerson() {
		return toPerson;
	}
	
	public void setToPerson(Person toPerson) {
		this.toPerson = toPerson;
	}
	
	public ConditonalParams getConditionalParams() {
		return conditionalParams;
	}
	
	public void setConditionalParams(ConditonalParams conditionalParams) {
		this.conditionalParams = conditionalParams;
	}
}
