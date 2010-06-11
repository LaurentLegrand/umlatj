package org.umlatj.internal.kernel;

public class KTypedElement extends KNamedElement {

	private final KType type;

	public KTypedElement(String name, KType type) {
		super(name);
		this.type = type;
	}

	public KType getType() {
		return type;
	}

}
