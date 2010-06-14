package org.umlatj.profile.behavior.petri;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Place {
	
	String value();
	
	int marking() default 0;

}
