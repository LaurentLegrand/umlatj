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
