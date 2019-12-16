package com.geeks.challenge.family.tree.rules.rigistry;

public class ConditonalParams {

	public enum Gender{
		MALE, FEMALE};
	public enum LookingFor{CHILDREN, ITSELF};
	public enum KindOf{THROUGH_BLOOD, THROUGH_MARRIAGE};
	
	private String relationName;
	private int generationGap;
	private Gender gender;
	private boolean isExclude;
	private LookingFor lookingFor;
	private KindOf kindOf;
	
	public ConditonalParams(ConditonalParamsBuilder conditonalParamsBuilder) {
		this.relationName = conditonalParamsBuilder.relation;
		this.generationGap = conditonalParamsBuilder.generationGap;
		this.gender = conditonalParamsBuilder.gender;
		this.isExclude = conditonalParamsBuilder.isExclude;
		this.lookingFor = conditonalParamsBuilder.lookingFor;
		this.kindOf  = conditonalParamsBuilder.kindOf;
	}

	public String getRelationName() {
		return relationName;
	}
	
	public int getGenerationGap() {
		return generationGap;
	}

	public Gender getGender() {
		return gender;
	}

	public boolean isExclude() {
		return isExclude;
	}
	
	public LookingFor getLookingFor() {
		return this.lookingFor;
	}
	
	public KindOf getKindOf() {
		return this.kindOf;
	}
	
	public static class ConditonalParamsBuilder {
		
		private String relation;
		private int generationGap;
		private Gender gender;
		private boolean isExclude;
		private LookingFor lookingFor;
		private KindOf kindOf;
		
		public ConditonalParamsBuilder(String relation) {
			this.relation = relation;
		}
		
		public ConditonalParamsBuilder withGenerationGap(int distance) {
			this.generationGap = distance;
			return this;
		}
		
		public ConditonalParamsBuilder withGender(Gender gender) {
			this.gender = gender;
			return this;
		}
		
		public ConditonalParamsBuilder withExclude(boolean type) {
			this.isExclude = type;
			return this;
		}
		
		public ConditonalParamsBuilder withLookingFor(LookingFor lookingFor) {
			this.lookingFor = lookingFor;
			return this;
		}
		
		public ConditonalParamsBuilder withKindOf(KindOf kindOf) {
			this.kindOf = kindOf;
			return this;
		}
		
		public ConditonalParams build() {
			ConditonalParams conditonalParams = new ConditonalParams(this);
			return conditonalParams;
		}
	}
}
