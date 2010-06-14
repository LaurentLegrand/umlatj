package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.util.proxy.MethodProxy;

/**
 * Constraint corresponding to a method with no arguments.
 * 
 * @author Laurent Legrand
 * 
 * @param <O>
 */
public class ThisConstraint extends KConstraint {

	MethodProxy<Boolean> method;

	public ThisConstraint(MethodProxy<Boolean> method) {
		super(method.getName());
		this.method = method;
	}

	@Override
	public boolean verify(Object self) {
		Boolean value = this.method.invoke(self);
		// to prevent NPE during auto boxing
		return (value == null) ? false : value;
	}

}
