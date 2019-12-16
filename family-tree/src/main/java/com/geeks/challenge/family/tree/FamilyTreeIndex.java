package com.geeks.challenge.family.tree;

import java.util.HashMap;
import java.util.Map;

public class FamilyTreeIndex {
	 
	private static FamilyTreeIndex familyTreeIndexInstance;
	private Map<String, Node> indexMap;
	
	private FamilyTreeIndex() {
		this.indexMap = new HashMap<String, Node>();
	}
	
	public Node getNode(String personName) {
		return indexMap.get(personName);
	}
	
	public void addNode(String name, Node node) {
		indexMap.put(name, node);
	}
	
	public static FamilyTreeIndex getFamilyTreeIndexInstance() {
		if(familyTreeIndexInstance == null) {
			familyTreeIndexInstance = new FamilyTreeIndex();
		}
		return familyTreeIndexInstance;
	}

}
