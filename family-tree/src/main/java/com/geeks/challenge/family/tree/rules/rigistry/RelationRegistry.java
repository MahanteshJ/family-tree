package com.geeks.challenge.family.tree.rules.rigistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelationRegistry {
	
	private static RelationRegistry relationRegistryInstance;
	
	private Map<String, Relation> relationRegistry = new HashMap<String, Relation>();
	
	public Relation getRelation(String relationName) {
		return relationRegistry.get(relationName);
	}
	
	public void put(Relation relation) {
		relationRegistry.put(relation.getName(), relation);
	}
	
	public void putAll(List<Relation> relations) {
		relations.forEach(relation -> relationRegistry.put(relation.getName(), relation));
	}
	
	public static RelationRegistry getRelationRegistryInstance() {
		if(relationRegistryInstance == null) {
			relationRegistryInstance = new RelationRegistry();
		}
		return relationRegistryInstance;
	}
}
