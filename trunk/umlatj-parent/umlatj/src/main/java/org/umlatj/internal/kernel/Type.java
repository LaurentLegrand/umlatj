package org.umlatj.internal.kernel;

import org.umlatj.internal.util.proxy.TypeProxy;

public class Type extends KNamedElement {

	TypeProxy type;

	public Type(TypeProxy type) {
		super(type.getName());
		this.type = type;
	}

	public TypeProxy getType() {
		return type;
	}
}
