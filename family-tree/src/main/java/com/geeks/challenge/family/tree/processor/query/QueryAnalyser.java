package com.geeks.challenge.family.tree.processor.query;

import com.geeks.challenge.family.tree.analyser.query.QueryParams;

public interface QueryAnalyser {
	
	public QueryParams analyse(String query);
	
}
