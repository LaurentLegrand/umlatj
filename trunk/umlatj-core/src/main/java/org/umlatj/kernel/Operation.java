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
