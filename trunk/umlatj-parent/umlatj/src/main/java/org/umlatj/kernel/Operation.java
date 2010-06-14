package org.umlatj.kernel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An operation is a behavioral feature of a classifier that specifies the name,
 * type, parameters, and constraints for invoking an associated behavior.
 * 
 * An operation may invoke both the execution of method behaviors as well as
 * other behavioral responses.
 * 
 * Operation specializes TemplateableElement in order to support specification
 * of template operations and bound operations. Operation specializes
 * ParameterableElement to specify that an operation can be exposed as a formal
 * template parameter, and provided as an actual parameter in a binding of a
 * template.
 * 
 * @author Laurent Legrand
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Operation {
	
	boolean query() default false;

}
