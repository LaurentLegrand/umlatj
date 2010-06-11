package org.umlatj.internal.util.aop;

public interface Advice {

	public void before(Object self, Object... args);

	public void afterReturning(Object self, Object returning, Object... args);

	public void afterThrowing(Object self, Exception e, Object... args);

	// public void around(E self);

}
