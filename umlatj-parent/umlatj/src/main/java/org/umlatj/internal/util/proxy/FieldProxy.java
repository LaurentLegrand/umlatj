package org.umlatj.internal.util.proxy;

public interface FieldProxy<E> {

	public String getName();

	public E get(Object self);

	public void set(Object self, E element);

}
