package org.umlatj.internal.reflect;


public privileged aspect Classifiers {

	after() : staticinitialization(@org.umlatj.kernel.Classifier *) {
		Types.asClassifier(thisJoinPoint.getSignature().getDeclaringType());
	}
}
