package org.umlatj.internal.util.proxy;

import java.beans.PropertyDescriptor;

public class PropertyBasedImpl<E> implements FieldProxy<E> {

	PropertyDescriptor descriptor;

	public PropertyBasedImpl(PropertyDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(Object self) {
		try {
			return (E) this.descriptor.getReadMethod().invoke(self);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getName() {
		return this.descriptor.getName();
	}

	@Override
	public void set(Object self, E element) {
		try {
			this.descriptor.getWriteMethod().invoke(self, element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
