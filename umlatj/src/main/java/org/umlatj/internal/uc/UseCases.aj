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
