package org.umlatj.profile.behavior.petri;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Net {
	
	Place[] values();
	
	int capacity() default Integer.MAX_VALUE;

}
