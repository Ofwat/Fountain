package uk.gov.ofwat.fountain.audit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SkipAudit {

}
