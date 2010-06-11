package org.umlatj.sm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * State machines can be used to express the behavior of part of a system.
 * KBehavior is modeled as a traversal of a graph of state nodes interconnected
 * by one or more joined transition arcs that are triggered by the dispatching
 * of series of (event) occurrences. During this traversal, the state machine
 * executes a series of activities associated with various elements of the state
 * machine.
 * 
 * @author Laurent Legrand
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StateMachine {

}
