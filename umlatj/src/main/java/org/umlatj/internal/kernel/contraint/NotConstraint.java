package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;

public class NotConstraint extends KConstraint {
	
	KConstraint constraint;
	
	public NotConstraint(KConstraint constraint) {
		super("not");
		this.constraint = constraint;
	}

	@Override
	public boolean verify(Object self) {
		return !this.constraint.verify(self);
	}

}
