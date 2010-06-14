package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.util.proxy.MethodProxy;

/**
 * Constraint corresponding to a static method with one parameter.
 * 
 * 
 * @author Laurent Legrand
 * 
 * @param <O>
 */
public class TargetConstraint extends KConstraint {

	MethodProxy<Boolean> method;

	public TargetConstraint(MethodProxy<Boolean> method) {
		super(method.getName());
		this.method = method;
	}

	@Override
	public boolean verify(Object self) {
		Boolean value = this.method.invoke(null, self);
		// to prevent NPE during auto boxing
		return (value == null) ? false : value;
	}

}
