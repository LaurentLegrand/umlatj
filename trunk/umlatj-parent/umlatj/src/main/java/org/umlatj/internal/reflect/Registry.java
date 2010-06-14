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
