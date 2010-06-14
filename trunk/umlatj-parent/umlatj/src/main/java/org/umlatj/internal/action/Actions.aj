package org.umlatj.internal.action;

import org.umlatj.internal.behavior.KBehavior;
import org.umlatj.internal.behavior.KAction;
import org.umlatj.internal.behavior.KBehavior.BehaviorInstance;

public privileged aspect Actions {

	pointcut action(Object self): execution(@org.umlatj.action.Action * *.*()) && this(self);

	before(Object self): action(self) {
		BehaviorInstance instance = KBehavior.adapt(self);
		if (instance != null) {
			instance.type.getAction(thisJoinPointStaticPart.getSignature().getName()).before(self);
		}
	}

	after(Object self) returning(Object callee): action(self) {
		BehaviorInstance instance = KBehavior.adapt(self);
		if (instance != null) {
			instance.type.getAction(thisJoinPointStaticPart.getSignature().getName())
			        .afterReturning(self, callee);
		}
	}

}
