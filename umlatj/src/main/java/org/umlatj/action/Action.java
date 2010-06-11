package org.umlatj.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Action {

	String preConditions() default "";

	String postConditions() default "";
	
	String[] inputs() default {};
	
	String[] outputs() default {};
	
}
