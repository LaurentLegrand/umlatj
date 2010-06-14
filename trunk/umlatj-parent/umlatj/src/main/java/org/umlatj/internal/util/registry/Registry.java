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

package org.umlatj.internal.util.registry;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Registry<E> {

	Set<Builder<? extends E>> builders = new HashSet<Builder<? extends E>>();

	Map<Class<?>, E> instances = new ConcurrentHashMap<Class<?>, E>();

	public Registry() {
	}

	public E get(Class<?> type) {
		E instance = this.instances.get(type);
		if (instance == null) {
			instance = this.build(type);
			this.instances.put(type, instance);
		}
		return instance;
	}

	E build(Class<?> type) {
		for (Builder<? extends E> builder : this.builders) {
			if (builder.accept(type)) {
				return builder.build(type);
			}
		}
		throw new IllegalArgumentException("No builder for type: " + type);
	}

	public void addBuilder(Builder<? extends E> builder) {
		this.builders.add(builder);
	}

}
