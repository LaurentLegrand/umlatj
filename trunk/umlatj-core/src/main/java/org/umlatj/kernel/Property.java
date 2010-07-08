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
@Target( { ElementType.FIELD, ElementType.METHOD })
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

	/**
	 * 
	 * 
	 */
	boolean isDerivedUnion() default false;

	/**
	 * 
	 * @return
	 */
	String subset() default "";

}
