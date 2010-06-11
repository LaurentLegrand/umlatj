package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;

public class FalseConstraint extends KConstraint {

	public FalseConstraint() {
		super("false");
	}

	@Override
	public boolean verify(Object self) {
		return false;
	}

}
