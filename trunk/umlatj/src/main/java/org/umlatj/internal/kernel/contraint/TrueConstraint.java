package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;

public class TrueConstraint extends KConstraint {

	public TrueConstraint() {
		super("true");
	}

	@Override
	public boolean verify(Object self) {
		return true;
	}

}
