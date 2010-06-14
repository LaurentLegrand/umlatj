/**
 * 
 */
package org.umlatj.internal.uc;

import java.lang.reflect.Method;

import org.umlatj.internal.util.Classes;
import org.umlatj.internal.util.proxy.MethodProxyImpl;
import org.umlatj.internal.util.proxy.TypeProxyImpl;
import org.umlatj.internal.util.registry.Builder;
import org.umlatj.uc.Extend;
import org.umlatj.uc.ExtensionPoint;
import org.umlatj.uc.UseCase;



public class UseCaseBuilder extends Builder<DeclaredUseCase> {

	public UseCaseBuilder() {
		super(UseCase.class);
	}

	public DeclaredUseCase build(Class<?> type) {
		// TODO manage constraints
		DeclaredUseCase useCase = new DeclaredUseCase(new TypeProxyImpl(type));
		for (Method method : Classes.findMethods(type, ExtensionPoint.class)) {
			DeclaredExtensionPoint extensionPoint = new DeclaredExtensionPoint(new MethodProxyImpl<Object>(
			        method));
			useCase.addExtensionPoint(extensionPoint);
		}

		for (Method method : Classes.findMethods(type, Extend.class)) {
			Extend extend = method.getAnnotation(Extend.class);
			DeclaredExtend rtExtend = new DeclaredExtend(new MethodProxyImpl<Object>(method));
			useCase.addExtend(rtExtend);

			DeclaredUseCase extension = DeclaredUseCase.get(extend.extension());
			DeclaredExtensionPoint extensionPoint = extension.getExtensionPoint(extend.location());
			if (extensionPoint != null) {
				extensionPoint.setExtend(rtExtend);
			}
		}
		return useCase;
	}

}