package com.geeks.challenge.family.tree;

import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams.Gender;

public class Person {
	private String name;
	private Gender gender;
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
