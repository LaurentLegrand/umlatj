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
