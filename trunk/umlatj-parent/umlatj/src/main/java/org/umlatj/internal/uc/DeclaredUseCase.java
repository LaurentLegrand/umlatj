package org.umlatj.internal.uc;

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.kernel.KType;
import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.internal.util.registry.Registry;


public class DeclaredUseCase extends KType {

	Map<String, DeclaredExtensionPoint> extensionPoints = new HashMap<String, DeclaredExtensionPoint>();

	Map<String, DeclaredExtend> extend = new HashMap<String, DeclaredExtend>();

	public DeclaredUseCase(TypeProxy type) {
		super(type);
	}

	public void addExtensionPoint(DeclaredExtensionPoint extensionPoint) {
		this.extensionPoints.put(extensionPoint.getName(), extensionPoint);
		extensionPoint.setUseCase(this);
	}

	public DeclaredExtensionPoint getExtensionPoint(String name) {
		return this.extensionPoints.get(name);
	}

	public void addExtend(DeclaredExtend extend) {
		this.extend.put(extend.getName(), extend);
		extend.setUseCase(this);
	}

	public DeclaredExtend getExtend(String name) {
		return this.extend.get(name);
	}
	
	static Registry<DeclaredUseCase> registry = new Registry<DeclaredUseCase>();

	public static DeclaredUseCase get(Class<?> type) {
		return registry.get(type);
	}
}
