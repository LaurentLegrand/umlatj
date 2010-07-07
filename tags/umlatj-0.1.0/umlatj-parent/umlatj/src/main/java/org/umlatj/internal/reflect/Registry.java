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

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Registry<E> {

	SortedSet<Builder<E>> builders = new TreeSet<Builder<E>>(
			new Comparator<Builder<E>>() {

				@Override
				public int compare(Builder<E> o1, Builder<E> o2) {
					return o2.getPriority() - o1.getPriority();
				}
			});

	/**
	 * Cache of instances
	 */
	Map<Class<?>, E> instances = new ConcurrentHashMap<Class<?>, E>();

	public Registry() {
	}

	public E get(Class<?> type) {
		E instance = this.instances.get(type);
		if (instance == null) {
			return this.build(type);
		}
		return instance;
	}

	E build(Class<?> type) {
		for (Builder<E> builder : this.builders) {
			if (builder.accept(type)) {
				E instance = builder.newInstance(type);
				this.instances.put(type, instance);
				builder.build(type, instance);
				return instance;
			}
		}
		throw new IllegalArgumentException("No builder for type: " + type);
	}

	public void addBuilder(Builder<E> builder) {
		this.builders.add(builder);
	}

}
