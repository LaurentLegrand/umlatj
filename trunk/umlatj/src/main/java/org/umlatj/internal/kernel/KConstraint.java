package org.umlatj.internal.kernel;

public abstract class KConstraint extends KNamedElement {

	public KConstraint(String name) {
		super(name);
	}

	public abstract boolean verify(Object self);

}
