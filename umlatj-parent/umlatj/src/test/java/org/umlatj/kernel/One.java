package org.umlatj.kernel;

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.util.proxy.FieldProxy;



public class One<E> implements FieldProxy<E> {

	Map<Object, E> map = new HashMap<Object, E>();
	
	public Map<Object, E> getMap() {
    	return map;
    }

	//@Override
    public E get(Object owner) {
	    return this.map.get(owner);
    }

	//@Override
    public String getName() {
	    return this.toString();
    }

	//@Override
    public void set(Object owner, E element) {
		if (element == null) {
			this.map.remove(owner);
		} else {
			this.map.put(owner, element);
		}
    }

}
