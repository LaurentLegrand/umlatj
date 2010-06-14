package org.umlatj.internal.kernel;

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.internal.util.registry.Registry;

public class KPackage extends KType {

	Map<String, KAssociation<?, ?>> associations = new HashMap<String, KAssociation<?, ?>>();

	public KPackage(TypeProxy type) {
		super(type);
	}

	public void addAssociation(KAssociation<?, ?> association) {
		this.associations.put(association.getName(), association);
		association.setPackage(this);
	}

	public KAssociation<?, ?> getAssociation(String name) {
		return this.associations.get(name);
	}

	static Registry<KPackage> registry = new Registry<KPackage>();

	public static KPackage get(Class<?> type) {
		return registry.get(type);
	}
}
