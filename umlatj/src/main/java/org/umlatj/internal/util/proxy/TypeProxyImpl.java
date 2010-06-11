package org.umlatj.internal.util.proxy;


public class TypeProxyImpl implements TypeProxy {

	Class<?> type;

	public TypeProxyImpl(Class<?> type) {
		this.type = type;
	}

	//@Override
	public String getName() {
		return this.type.getName();
	}

	//@Override
	public Class<?> getType() {
		return this.type;
	}

}
