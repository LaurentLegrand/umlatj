package org.umlatj.profile.behavior.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Basic {

	/**
	 * Goto 
	 * 
	 * @return
	 */
	String value();

}
