package org.umlatj.uc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.umlatj.action.Action;


/**
 * A relationship from an extending use case to an extended use case that
 * specifies how and when the behavior defined in the extending use case can be
 * inserted into the behavior defined in the extended use case.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Extend {

	/**
	 * The class where the {@link ExtensionPoint} is defined.
	 * 
	 * @return
	 */
	Class<?> extension();

	/**
	 * The name of the {@link Action} within the {@link #type()}.
	 * 
	 * @return
	 */
	String location();

}
