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
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A classifier is a classification of instances, it describes a set of
 * instances that have features in common.
 * 
 * A classifier is a namespace whose members can include features. Classifier is
 * an abstract metaclass.
 * 
 * A classifier is a type and can own generalizations, thereby making it
 * possible to define generalization relationships to other classifiers. A
 * classifier can specify a generalization hierarchy by referencing its general
 * classifiers.
 * 
 * A classifier is a redefinable element, meaning that it is possible to
 * redefine nested classifiers.
 * 
 * @author Laurent Legrand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Classifier {

}
