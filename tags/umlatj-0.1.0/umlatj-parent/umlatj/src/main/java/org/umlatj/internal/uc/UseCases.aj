/**
 * uml@J, UML annotations for Java
 *
 * Copyright (C) 2010 Laurent Legrand or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

package org.umlatj.internal.uc;

import org.umlatj.internal.uc.DeclaredExtensionPoint;
import org.umlatj.internal.uc.DeclaredUseCase;
import org.umlatj.internal.uc.UseCaseBuilder;

public privileged aspect UseCases {

	after() : staticinitialization(DeclaredUseCase) {
		DeclaredUseCase.registry.addBuilder(new UseCaseBuilder());
	}
	
	static privileged aspect PerUseCase pertypewithin(@org.umlatj.uc.UseCase *) {

		DeclaredUseCase useCase;

		after() : staticinitialization(@org.umlatj.uc.UseCase *) {
			this.useCase = DeclaredUseCase.get(thisJoinPoint.getSignature().getDeclaringType());
		}
		
		Object around(Object self) : call (*.new()) && this(self) && @withincode(org.umlatj.uc.ExtensionPoint) {
			System.out.println(thisEnclosingJoinPointStaticPart.toLongString());
			System.out.println(thisJoinPoint.toLongString());
			DeclaredExtensionPoint extensionPoint = this.useCase.getExtensionPoint(thisEnclosingJoinPointStaticPart.getSignature().getName());
			Object extend = (extensionPoint != null) ? extensionPoint.newExtend(self) : null;
			return (extend != null) ? extend : proceed(self);
		}
				
	}
}
