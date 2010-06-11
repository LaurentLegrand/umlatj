package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;

public class AndConstraint extends KConstraint {

	KConstraint left;

	KConstraint right;

	public AndConstraint(KConstraint left, KConstraint right) {
		super("and");
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean verify(Object self) {
		return this.left.verify(self) && this.right.verify(self);
	}

}
