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

package org.umlatj.internal.kernel;

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.kernel.contraint.ConstraintBuilder;
import org.umlatj.internal.util.proxy.TypeProxy;

public class KClassifier extends KType {

	Map<String, KProperty<?>> attributes = new HashMap<String, KProperty<?>>();

	Map<String, KConstraint> constraints = new HashMap<String, KConstraint>();

	ConstraintBuilder constraintBuilder = null;
	
//	new ConstraintBuilder() {
//
//		@Override
//		protected KConstraint fromLiteral(Literal literal) {
//			/*
//			 * String value = literal.getValue(); value = "is" +
//			 * Character.toUpperCase(value.charAt(0)) + value.substring(1);
//			 * return KClassifier.this.getConstraint(value);
//			 */
//			return new TrueConstraint();
//		}
//
//	};

	public KClassifier(TypeProxy type) {
		super(type);
	}

	public void addConstraint(KConstraint constraint) {
		this.constraints.put(constraint.getName(), constraint);
	}

	public KConstraint getConstraint(String name) {
		return this.constraints.get(name);
	}

	public void addAttribute(KProperty<?> property) {
		this.attributes.put(property.getName(), property);
	}

	public KProperty<?> getAttribute(String name) {
		return this.attributes.get(name);
	}

	public ConstraintBuilder getConstraintBuilder() {
		return constraintBuilder;
	}

}
