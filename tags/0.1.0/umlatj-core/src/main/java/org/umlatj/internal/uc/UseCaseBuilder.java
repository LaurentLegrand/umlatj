//
// uml@J, UML annotations for Java
//
// Copyright (C) 2010 Laurent Legrand or third-party contributors as
// indicated by the @author tags or express copyright attribution
// statements applied by the authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
//

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