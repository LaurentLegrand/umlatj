package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;

public class OrConstraint extends KConstraint {

	KConstraint left;

	KConstraint right;

	public OrConstraint(KConstraint left, KConstraint right) {
		super("or");
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean verify(Object self) {
		return this.left.verify(self) || this.right.verify(self);
	}

}
