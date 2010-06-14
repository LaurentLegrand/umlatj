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

package org.umlatj.internal.reflect;

import java.util.ServiceLoader;

import org.umlatj.internal.behavior.KBehavior;
import org.umlatj.internal.kernel.KClassifier;
import org.umlatj.internal.kernel.KPackage;
import org.umlatj.internal.util.Classes;

/**
 * Class that adapt a Java type to a UML one.
 * 
 * @author Laurent Legrand
 * 
 */
public class Types {

	static Registry<KClassifier> classifiers;

	static Registry<KPackage> packages;

	static Registry<KBehavior> behaviors;

	static {
		init();
	}

	@SuppressWarnings("unchecked")
	private static void init() {
		classifiers = new Registry<KClassifier>();
		packages = new Registry<KPackage>();
		behaviors = new Registry<KBehavior>();

		ServiceLoader<Builder> loader = ServiceLoader.load(Builder.class);
		for (Builder builder : loader) {
			Class<?> type = Classes.getTypeArguments(Builder.class,
					builder.getClass()).get(0);
			if (KClassifier.class.isAssignableFrom(type)) {
				classifiers.addBuilder(builder);
			} else if (KPackage.class.isAssignableFrom(type)) {
				packages.addBuilder(builder);
			} else if (KBehavior.class.isAssignableFrom(type)) {
				behaviors.addBuilder(builder);
			}
		}
	}

	/**
	 * Adapt a class to a {@link KClassifier}.
	 * 
	 * @param type
	 * @return
	 */
	public static KClassifier asClassifier(Class<?> type) {
		return classifiers.get(type);
	}

	/**
	 * Adapt a class to a {@link KClassifier}.
	 * 
	 * @param type
	 * @return
	 */
	public static KPackage asPackage(Class<?> type) {
		return packages.get(type);
	}

}