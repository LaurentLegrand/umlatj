//
// uml@J, UML annotations for Java
//
// Copyright (C) 2010 Laurent Legrand or third-party contributors as
// indicated by the @author tags or express copyright attribution
// statements applied by the authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
//

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

	/**
	 * The initial state of the machine
	 * 
	 * @return
	 */
	String initial() default "";

	/**
	 * The enum that contains the list of available states
	 * 
	 * @return
	 */
	Class<? extends Enum<?>> states();

}
