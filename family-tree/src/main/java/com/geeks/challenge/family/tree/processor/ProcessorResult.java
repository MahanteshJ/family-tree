package com.geeks.challenge.family.tree.processor;

public class ProcessorResult<T> {

	T resultLines;
	
	
	public ProcessorResult(T result) {
		super();
		this.resultLines = result;
	}
	
	
	public String getResult() {
		return resultLines.toString();
	}
}
