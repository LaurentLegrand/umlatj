package org.umlatj.internal.kernel;

import org.umlatj.internal.util.proxy.TypeProxy;

public class KType extends KNamedElement {

	TypeProxy type;

	public KType(TypeProxy type) {
		super(type.getName());
		this.type = type;
	}

	public TypeProxy getType() {
		return type;
	}
	
	

}
