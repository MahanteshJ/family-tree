package com.geeks.challenge.family.tree.utils;

import java.util.List;

import com.geeks.challenge.family.tree.Person;

public class FamilyTreeUtils {

	public static boolean isOlderGeneration(int curr, int req) {
		return req < (curr);
	}	
	
	public static boolean isLaterGeneration(int curr, int req) {
		return req < (curr);
	}

	public static String getByline(List<Person> persons) {
		StringBuilder sb = new StringBuilder();
		persons.forEach(person -> sb.append(person.getName()).append("\n"));
		sb.replace(sb.length()-1, sb.length(), "");
		return sb.toString();
	}
}
