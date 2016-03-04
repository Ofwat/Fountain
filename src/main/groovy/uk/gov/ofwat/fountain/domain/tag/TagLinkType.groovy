package uk.gov.ofwat.fountain.domain.tag

import org.apache.commons.lang.builder.HashCodeBuilder;

class TagLinkType {
	long id
	String entityType
	
	public TagLinkType(){
	}
	public TagLinkType(String entityType){
        this.entityType = entityType;
    }
	
	@Override
	public String toString() {
		return entityType;
	}

	@Override
	public int hashCode() {
       return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(entityType).
            toHashCode();
	}

	public boolean equals(String entityType) {
		if (entityType == null)
			return false;
		if (this.entityType == null) {
			if (entityType != null)
				return false;
		} else if (!this.entityType.equalsIgnoreCase(entityType))
			return false;
		return true;
	}
	
	public boolean equalsIgnoreCase(String entityType) {
		if (entityType == null)
			return false;
		if (this.entityType == null) {
			if (entityType != null)
				return false;
		} else if (!this.entityType.equalsIgnoreCase(entityType))
			return false;
		return true;
	}

	public boolean equals(Object obj) {
		//if (this == obj)
		//	return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagLinkType other = (TagLinkType) obj;
		if (this.entityType == null) {
			if (other.entityType != null)
				return false;
		} else if (!this.entityType.equals(other.entityType))
			return false;
		return true;
	}	

	public boolean equalsIgnoreCase(Object obj) {
		//if (this == obj)
		//	return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagLinkType other = (TagLinkType) obj;
		if (this.entityType == null) {
			if (other.entityType != null)
				return false;
		} else if (!this.entityType.equalsIgnoreCase(other.entityType))
			return false;
		return true;
	}
		
}
