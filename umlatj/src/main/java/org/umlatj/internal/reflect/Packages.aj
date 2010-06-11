package org.umlatj.internal.reflect;

import org.umlatj.internal.kernel.KPackage;
import org.umlatj.kernel.Package;

public aspect Packages {
	
	public static aspect PerPackage pertypewithin(@Package *){
		
		KPackage kPackage;
	
		after() : staticinitialization(@Package *) {
			this.kPackage = Types.asPackage(thisJoinPoint.getSignature().getDeclaringType());
		}

		Object around(): get(org.umlatj.kernel.Association.Binary *) {
			return this.kPackage.getAssociation(thisJoinPointStaticPart.getSignature().getName());
		}
		
		Object around(): get(org.umlatj.kernel.Association.Hierarchy *) {
			return this.kPackage.getAssociation(thisJoinPointStaticPart.getSignature().getName());
		}
	}
}
