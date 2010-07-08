package org.umlatj.kernel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.util.proxy.FieldProxy;


public class Many<E> implements FieldProxy<Collection<E>> {

	Map<Object, Collection<E>> map = new HashMap<Object, Collection<E>>();

	//@Override
	public Collection<E> get(Object owner) {
		Collection<E> collection = this.map.get(owner);
		if (collection == null) {
			collection = new ArrayList<E>();
			this.map.put(owner, collection);
		}
		return collection;
	}

	public void set(Object owner, java.util.Collection<E> element) {
		// ignore
	}

	//@Override
	public String getName() {
		return this.toString();
	}

	public Map<Object, Collection<E>> getMap() {
    	return map;
    }

}
