package uk.gov.ofwat.fountain.audit;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class AuditExclusionStrategy implements ExclusionStrategy {

	private final Class<?> typeToSkip;
	
	public AuditExclusionStrategy(Class<?> typeToSkip) {
		this.typeToSkip = typeToSkip;
	}
	
	public AuditExclusionStrategy(){
		this.typeToSkip = null;
	}	
	
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(SkipAudit.class) != null;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return (clazz == typeToSkip);
	}

}
