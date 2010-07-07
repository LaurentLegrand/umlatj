/**
 * uml@J, UML annotations for Java
 *
 * Copyright (C) 2010 Laurent Legrand or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

package org.umlatj.internal.reflect;

import org.umlatj.internal.kernel.KClassifier;
import org.umlatj.internal.kernel.KProperty;
import org.umlatj.internal.util.Beans;
import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Property;

public privileged aspect Classifiers {

	public static aspect PerPackage pertypewithin(@Classifier *){

		KClassifier classifier;

		after() : staticinitialization(@Classifier *) {
			this.classifier = Types.asClassifier(thisJoinPoint.getSignature()
					.getDeclaringType());
		}

		@SuppressWarnings("unchecked")
		Object around(Object self, Property property): 
			execution(@Property * get*()) && this(self) && @annotation(property) && if(property.isDerivedUnion()) {
			KProperty p = this.classifier.getAttribute(Beans.getPropertyName(thisJoinPointStaticPart
					.getSignature().getName()));
			return p.union(self);
		}

	}
}
