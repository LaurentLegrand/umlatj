package org.umlatj.internal.util.proxy;

import java.lang.reflect.Method;


public class MethodProxyImpl<E> implements MethodProxy<E> {

	Method method;

	public MethodProxyImpl(Method method) {
		this.method = method;
		this.method.setAccessible(true);
	}

	//@Override
	public String getName() {
		return this.method.getName();
	}

	@SuppressWarnings("unchecked")
	//@Override
	public E invoke(Object owner, Object... args) {
		try {
			return (E) this.method.invoke(owner, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
