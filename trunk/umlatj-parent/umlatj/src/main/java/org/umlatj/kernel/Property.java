package org.umlatj.kernel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A property is a structural feature.
 * 
 * A property related to a classifier by ownedAttribute represents an attribute,
 * and it may also represent an association end. It relates an instance of the
 * class to a value or collection of values of the type of the attribute.
 * 
 * A property related to an Association by memberEnd or its specializations
 * represents an end of the association. The type of property is the type of the
 * end of the association.
 * 
 * @author Laurent Legrand
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {

	/**
	 * Specifies the kind of aggregation that applies to the Property. The
	 * default value is none.
	 */
	AggregationKind aggregation() default AggregationKind.none;

	/**
	 * An optional list of ordered qualifier attributes for the end. If the list
	 * is empty, then the Association is not qualified.
	 */
	String qualifier() default "";

}
