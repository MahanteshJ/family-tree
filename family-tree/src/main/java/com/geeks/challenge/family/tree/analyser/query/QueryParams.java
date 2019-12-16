package com.geeks.challenge.family.tree.analyser.query;

import java.io.Serializable;
import java.util.Map;

public class QueryParams implements Serializable {
	private static final long serialVersionUID = 7738093652015779372L;
	
	
	private String action;
	private String relationName;
	private Map<String, String> arguments;
	
	public String getRelationName() {
		return relationName;
	}
	
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}
	public Map<String, String> getArguments() {
		return arguments;
	}
	
	public void setArguments(Map<String, String> arguments) {
		this.arguments = arguments;
	}
}
