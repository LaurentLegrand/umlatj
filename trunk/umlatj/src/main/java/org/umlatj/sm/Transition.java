package org.umlatj.sm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <blockquote cite="http://www.omg.org/">
 * A transition is a directed relationship between a source vertex and a target
 * vertex. It may be part of a compound transition, which takes the state
 * machine from one state configuration to another, representing the complete
 * response of the state machine to an occurrence of an event of a particular
 * type.
 * </blockquote>
 * 
 * <h1>Constraints</h1>
 * <ul>
 * <li>The method must have no arguments.</li>
 * </ul>
 * 
 * @author Laurent Legrand
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.METHOD })
public @interface Transition {

	/**
	 * Designates the originating vertex (state or pseudostate) of the
	 * transition.
	 */
	String source();

	/**
	 * Designates the target vertex that is reached when the transition is
	 * taken.
	 */
	String target();

	/*
	 * @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD) public
	 * @interface Trigger { }
	 */

}
