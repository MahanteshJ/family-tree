package com.geeks.challenge.family.tree.analyser.query;

import java.util.HashMap;
import java.util.Map;

import com.geeks.challenge.family.tree.constants.ApplicationConstants;
import com.geeks.challenge.family.tree.processor.query.QueryAnalyser;

public class QueryAnalyserImpl implements QueryAnalyser {

	public QueryParams analyse(String query) {
		String[] arguments = query.split(" ");
		QueryParams params = new QueryParams();
		params.setAction(arguments[0]);
		
		switch (params.getAction()) {
			case ApplicationConstants.ADD_MARRIED_MEMBER : {
				Map<String, String> argumentMap = new HashMap<String, String>();
				argumentMap.put(ApplicationConstants.COMES_THROUGH_MARRIAGE_NAME, arguments[1]);
				argumentMap.put(ApplicationConstants.COMES_THROUGH_MARRIAGE_GENDER, arguments[2]);
				argumentMap.put(ApplicationConstants.FAMILY_PERSON, arguments[3]);
				params.setArguments(argumentMap);
				break;
			} 
		
			case ApplicationConstants.ADD_CHILD : {
				Map<String, String> argumentsMap = new HashMap<String, String>();
				argumentsMap.put(ApplicationConstants.PARENT_NAME, arguments[2]);
				argumentsMap.put(ApplicationConstants.CHILD_NAME, arguments[1]);
				argumentsMap.put(ApplicationConstants.CHILD_GENDER, arguments[3]);
				params.setArguments(argumentsMap);
				break;
			}
			case ApplicationConstants.ADD_ROOT : {
				Map<String, String> argumentsMap = new HashMap<String, String>();
				argumentsMap.put(ApplicationConstants.ROOT_NAME, arguments[1]);
				argumentsMap.put(ApplicationConstants.ROOT_GENDER, arguments[2]);
				params.setArguments(argumentsMap);
				break;
			}
			
			case ApplicationConstants.GET_RELATION : {
				params.setRelationName(arguments[2]);
				Map<String, String> argumentsMap = new HashMap<String, String>();
				argumentsMap.put(ApplicationConstants.RELATION_FROM, arguments[1]);
				argumentsMap.put(ApplicationConstants.RELATION_NAME, arguments[2]);
				params.setArguments(argumentsMap);	
				break;
			}
			default:
		}
		
		return params;
	}
}
