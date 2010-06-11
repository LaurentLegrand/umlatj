package org.umlatj.profile.behavior.petri;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Arc {
	
	String value();
	
	String guard() default "";
	
	int weight() default 1;

}
