package com.geeks.challenge.family.tree.processor.query;

import com.geeks.challenge.family.tree.FamilyTreeIndex;
import com.geeks.challenge.family.tree.Node;
import com.geeks.challenge.family.tree.analyser.query.QueryParams;
import com.geeks.challenge.family.tree.constants.ApplicationConstants;
import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams;
import com.geeks.challenge.family.tree.rules.rigistry.Relation;
import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams.ConditonalParamsBuilder;
import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams.Gender;
import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams.KindOf;
import com.geeks.challenge.family.tree.rules.rigistry.ConditonalParams.LookingFor;

public class RelationBuilder {
	private FamilyTreeIndex familyTreeIndexReference = FamilyTreeIndex.getFamilyTreeIndexInstance();

	public Relation build(QueryParams params){
		Relation relation = new Relation();
		relation.setName(params.getRelationName());
		ConditonalParams.ConditonalParamsBuilder builder = new ConditonalParamsBuilder(relation.getName());
		ConditonalParams conditonalParams = null;
		switch(params.getRelationName()) {
			case "Paternal-Uncle" : {
			conditonalParams = builder.withGenerationGap(-2).withGender(Gender.MALE).withKindOf(KindOf.THROUGH_BLOOD)
					.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
				break;
			}
			
			case "Maternal-Uncle" : {
				conditonalParams = builder.withGenerationGap(-2).withGender(Gender.MALE).withKindOf(KindOf.THROUGH_BLOOD)
						.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
				break;
			}
			
			case "Paternal-Aunt" : {
				conditonalParams = builder.withGenerationGap(-2).withGender(Gender.FEMALE).withKindOf(KindOf.THROUGH_BLOOD)
						.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
				break;
			}
			
			case "Maternal-Aunt" : {
				conditonalParams = builder.withGenerationGap(-2).withGender(Gender.FEMALE).withKindOf(KindOf.THROUGH_BLOOD)
						.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
				break;
			}
			
			case "Sister-In-Law" : {
				Node node = familyTreeIndexReference.getNode(params.getArguments().get(ApplicationConstants.RELATION_FROM));
				if (node.getByBloodRelated().getName().equals(params.getArguments().get(ApplicationConstants.RELATION_FROM))) {
					conditonalParams = builder.withGenerationGap(-1).withGender(Gender.FEMALE).withKindOf(KindOf.THROUGH_MARRIAGE)
							.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
					break;	
				}
				
				if (node.getByMarriage().getName().equals(params.getArguments().get(ApplicationConstants.RELATION_FROM))) {
					conditonalParams = builder.withGenerationGap(-1).withGender(Gender.FEMALE).withKindOf(KindOf.THROUGH_BLOOD)
							.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
					break;
				}
			}
			
			case "Brother-In-Law" : {
				Node node = familyTreeIndexReference.getNode(params.getArguments().get(ApplicationConstants.RELATION_FROM));
				if (node.getByBloodRelated().getName().equals(params.getArguments().get(ApplicationConstants.RELATION_FROM))) {
					conditonalParams = builder.withGenerationGap(-1).withGender(Gender.MALE).withKindOf(KindOf.THROUGH_MARRIAGE)
							.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
					break;	
				}
				
				if (node.getByMarriage().getName().equals(params.getArguments().get("PersonRelationFrom"))) {
					conditonalParams = builder.withGenerationGap(-1).withGender(Gender.MALE).withKindOf(KindOf.THROUGH_BLOOD)
							.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
					break;
				}
			}
			
			case "Son" : {
				conditonalParams = builder.withGenerationGap(0).withGender(Gender.MALE).withKindOf(KindOf.THROUGH_BLOOD)
						.withLookingFor(LookingFor.CHILDREN).withExclude(false).build();
				break;
			}
			
			case "Daughter" : {
				conditonalParams = builder.withGenerationGap(0).withGender(Gender.FEMALE).withKindOf(KindOf.THROUGH_BLOOD)
						.withLookingFor(LookingFor.CHILDREN).withExclude(false).build();
				break;
			}
			
			case "Siblings" : {
				conditonalParams = builder.withGenerationGap(-1).withKindOf(KindOf.THROUGH_BLOOD)
						.withLookingFor(LookingFor.CHILDREN).withExclude(true).build();
				break;
			}
		}
		relation.setConditionalParams(conditonalParams);
		return relation;

	}
}
