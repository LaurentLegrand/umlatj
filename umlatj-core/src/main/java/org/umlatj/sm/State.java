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
 * A state models a situation during
 * which some (usually implicit) invariant condition holds. The invariant may
 * represent a static situation such as an object waiting for some external
 * event to occur. However, it can also model dynamic conditions such as the
 * process of performing some behavior (i.e., the model element under
 * consideration enters the state when the behavior commences and leaves it as
 * soon as the behavior is completed).
 * </blockquote>
 * 
 * <h1>Constraints</h1>
 * <ul>
 * <li>The field must not be final</li>
 * <li>The type of the field must be an enumeration</li>
 * </ul>
 * 
 * @author Laurent Legrand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface State {

	/**
	 * An optional behavior that is executed whenever this state is entered
	 * regardless of the transition taken to reach the state. If defined, entry
	 * actions are always executed to completion prior to any internal behavior
	 * or transitions performed within the state.
	 * 
	 * @author Laurent Legrand
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface Entry {
		String value();
	}

	/**
	 * An optional behavior that is executed whenever this state is exited
	 * regardless of which transition was taken out of the state. If defined,
	 * exit actions are always executed to completion only after all internal
	 * activities and transition actions have completed execution.
	 * 
	 * @author Laurent Legrand
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface Exit {
		String value();
	}
}