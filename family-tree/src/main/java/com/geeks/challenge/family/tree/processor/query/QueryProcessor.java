package com.geeks.challenge.family.tree.processor.query;

import static com.geeks.challenge.family.tree.utils.ObjectUtils.isNull;

import java.util.List;

import com.geeks.challenge.family.tree.FamilyTree;
import com.geeks.challenge.family.tree.FamilyTreeIndex;
import com.geeks.challenge.family.tree.Node;
import com.geeks.challenge.family.tree.Person;
import com.geeks.challenge.family.tree.analyser.query.QueryAnalyserImpl;
import com.geeks.challenge.family.tree.analyser.query.QueryParams;
import com.geeks.challenge.family.tree.constants.ApplicationConstants;
import com.geeks.challenge.family.tree.processor.ProcessorResult;
import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams.Gender;
import com.geeks.challenge.family.tree.rules.rigistry.Relation;
import com.geeks.challenge.family.tree.rules.rigistry.RelationRegistry;
import com.geeks.challenge.family.tree.utils.FamilyTreeUtils;


public class QueryProcessor {
	
	private QueryAnalyser analyser = new QueryAnalyserImpl();
	
	private FamilyTreeIndex familyTreeIndexReference = FamilyTreeIndex.getFamilyTreeIndexInstance();
	
	private FamilyTree familyTreeReference = FamilyTree.getFamilyTreeInstance();
	
	private RelationRegistry relationRegistryReference = RelationRegistry.getRelationRegistryInstance();
	
	private RelationBuilder relationBuilder  = new RelationBuilder();
	
	private QueryParams params;
		
	public ProcessorResult<?> process( String query) {
		params = analyser.analyse(query);
		
		if (params.getAction().equals(ApplicationConstants.ADD_ROOT)) {
			Person person = new Person(params.getArguments().get(ApplicationConstants.ROOT_NAME));
			person.setGender(Gender.valueOf(params.getArguments().get(ApplicationConstants.ROOT_GENDER)));
			Node parentNode = new Node(person);
			familyTreeReference.addRootNode(parentNode);
			familyTreeIndexReference.addNode(person.getName(), parentNode);
			return new ProcessorResult<String>("ROOT_ADDITION_SUCCEEDED");
		}
		
		if (params.getAction().equals(ApplicationConstants.ADD_CHILD)) {
			Node parentNode = familyTreeIndexReference.getNode(params.getArguments().get(ApplicationConstants.PARENT_NAME));	
//			if(isNull(parentNode)) {
//				return new ProcessorResult<String>("PERSON_NOT_FOUND");
//			}
			Person person = new Person(params.getArguments().get(ApplicationConstants.CHILD_NAME));
			person.setGender(Gender.valueOf(params.getArguments().get(ApplicationConstants.CHILD_GENDER)));
			Node childNode = new Node(person);
			familyTreeReference.addNode(parentNode, childNode);
			familyTreeIndexReference.addNode(person.getName(), childNode);
			return new ProcessorResult<String>("CHILD_ADDITION_SUCCEEDED");
		}
		
		if (params.getAction().equals(ApplicationConstants.ADD_MARRIED_MEMBER)) {
			Node familyMembernode  = familyTreeIndexReference.getNode(params.getArguments().get(ApplicationConstants.FAMILY_PERSON));
		
			if (isNull(familyMembernode)) {
				return new ProcessorResult<String>("PERSON_NOT_FOUND");
			}
	
			String personName = params.getArguments().get(ApplicationConstants.COMES_THROUGH_MARRIAGE_NAME);
			Person person = new Person(personName);
			person.setGender(Gender.valueOf(params.getArguments().get(ApplicationConstants.COMES_THROUGH_MARRIAGE_GENDER)));
			familyMembernode.setByMarriage(person);
			familyTreeIndexReference.addNode(person.getName(), familyMembernode);
			
			return new ProcessorResult<String>("MARRIED_PERSON_ADDITION_SUCCEEDED");
		}
		
		if (params.getAction().equals(ApplicationConstants.GET_RELATION)) {
			Node node = familyTreeIndexReference.getNode(params.getArguments().get(ApplicationConstants.RELATION_FROM));
			if(node==null) {
				return new ProcessorResult<String>("PERSON_NOT_FOUND");
			}
			if(isValidRelation(params, node.getParent())) {
				Relation relation = relationRegistryReference.getRelation(params.getRelationName());
				if (isNull(relation)) {
					relation = relationBuilder.build(params);
					relationRegistryReference.put(relation);
				}
				
				List<Person> persons = familyTreeReference.getRelatedPersons(relation, node);
				return new ProcessorResult<String>(FamilyTreeUtils.getByline(persons));	
			}
			return new ProcessorResult<String>("NONE");
			
		}
		
		return new ProcessorResult("Relation Action not found or not supported...");
	}

	private boolean isValidRelation(QueryParams params, Node node) {
		if(node == null) {
			return false;
		}
		switch (params.getRelationName()) {
			case "Maternal-Uncle": {
					if(node.getByMarriage()!=null && node.getByMarriage().getGender().equals(Gender.FEMALE)) {
						return false;
					}
					break;
			}
			case "Paternal-Uncle": {
				if(node.getByMarriage()!=null && node.getByMarriage().getGender().equals(Gender.MALE)) {
					return false;
				}
				break;
			}
			case "Paternal-Aunt": {
				if(node.getByMarriage()!=null && node.getByMarriage().getGender().equals(Gender.MALE)) {
					return false;
				}
				break;
			}
			case "Maternal-Aunt": {
				if(node.getByMarriage()!=null && node.getByMarriage().getGender().equals(Gender.FEMALE)) {
					return false;
				}
				break;
			}
			case "Sister-In-Law":{
				if(node.getByBloodRelated().getName().equals(params.getArguments().get(ApplicationConstants.RELATION_FROM))) {
					return false;
				}
				
			}
			case "Brother-In-Law":{
				if(node.getByBloodRelated().getName().equals(params.getArguments().get(ApplicationConstants.RELATION_FROM))) {
					return false;
				}
			}
			default: return true;
		}
		return true;
	}
	
}
