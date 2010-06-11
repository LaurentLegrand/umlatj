package org.umlatj.internal.util.proxy;

import java.lang.reflect.Field;


public class FieldProxyImpl<E> implements FieldProxy<E> {

	Field field;

	public FieldProxyImpl(Field field) {
		this.field = field;
		this.field.setAccessible(true);
	}
	
	//@Override
	public String getName() {
	    return this.field.getName();
	}

	@SuppressWarnings("unchecked")
    //@Override
	public E get(Object owner) {
		try {
			return (E) this.field.get(owner);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//@Override
	public void set(Object owner, E element) {
		try {
			this.field.set(owner, element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
