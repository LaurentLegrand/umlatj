package org.umlatj.internal.util.proxy;

public interface MethodProxy<E> {

	public String getName();

	public E invoke(Object self, Object... args);

}
