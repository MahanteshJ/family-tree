package com.geeks.challenge.family.tree;

import java.util.ArrayList;
import java.util.List;
import static com.geeks.challenge.family.tree.utils.FamilyTreeUtils.isLaterGeneration;
import static com.geeks.challenge.family.tree.utils.FamilyTreeUtils.isOlderGeneration;
import static com.geeks.challenge.family.tree.utils.ObjectUtils.isNull;

import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams;
import com.geeks.challenge.family.tree.rules.rigistry.Relation;

import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams.KindOf;
import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams.LookingFor;

public class FamilyTree {

	private Node root;
	private static FamilyTree familyTreeInstance;
	
	private FamilyTree() {}
	
	public void addRootNode(Node node) {
		if(root == null) {
			root = node;
		}
	}
	
	public void addNode(Node parentNode, Node node) {
		if (parentNode == null ) {
			addRootNode(node);
		} else {
			node.setParent(parentNode);
			parentNode.getChildren().add(node);
		}
	}
	
	public List<Person> getRelatedPersons(Relation relation, Node node) {
		return getRelatedPersons(relation.getConditionalParams(), node, 0);
	}
	
	private List<Person> getRelatedPersons(ConditonalParams conditionalParams,  Node curr, int currentPos) {
			List<Person> persons = new ArrayList<Person>();
			if(isOlderGeneration(currentPos, conditionalParams.getGenerationGap())) {
				currentPos -= 1;
				List<Person> children = getRelatedPersons(conditionalParams, curr.getParent(), currentPos);
				if(conditionalParams.isExclude()) {
					children.remove(curr.getByBloodRelated());
					children.remove(curr.getByMarriage());
				}
				persons.addAll(children);
			} else if(isLaterGeneration(currentPos, conditionalParams.getGenerationGap())) {
				currentPos += 1;
				persons.addAll(getRelatedPersons(conditionalParams, curr.getParent(), currentPos));
			
			} else {
				
				if(conditionalParams.getLookingFor() == LookingFor.CHILDREN) {
					List<Person> children  = getChildren(conditionalParams, curr);
					if(conditionalParams.isExclude()) {
						children.remove(curr.getByBloodRelated());
						children.remove(curr.getByMarriage());
					}
					persons.addAll(children);
					
					
				} 
				
				if(conditionalParams.getLookingFor() == LookingFor.ITSELF) {
					Person person = (conditionalParams.getKindOf() == KindOf.THROUGH_BLOOD)? curr.getByBloodRelated():curr.getByBloodRelated();
					persons.add(person);
				}
			}
		return persons;
	}
	
	private List<Person> getChildren(final ConditonalParams conditionalParams, Node node) {
		List<Person> ret = new ArrayList<Person>();
		for(Node child : node.getChildren()) {
			if(!isNull(conditionalParams.getGender())) {
					Person person = (conditionalParams.getKindOf() == KindOf.THROUGH_BLOOD)? child.getByBloodRelated():child.getByMarriage();
					if (person!=null && person.getGender().equals(conditionalParams.getGender())) {
						ret.add(person);
					}
				
			} else {
					Person person = (conditionalParams.getKindOf() == KindOf.THROUGH_BLOOD)? child.getByBloodRelated():child.getByMarriage();
					ret.add(person);
			}
			
		}
		return ret;
	}
	
	public static FamilyTree getFamilyTreeInstance() {
		if(familyTreeInstance == null) {
			familyTreeInstance = new FamilyTree();
		} 
		return familyTreeInstance;
	}
}
