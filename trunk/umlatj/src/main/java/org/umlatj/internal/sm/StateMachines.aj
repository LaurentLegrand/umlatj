package org.umlatj.internal.sm;

import org.umlatj.kernel.*;
import org.umlatj.sm.*;

public privileged aspect StateMachines {

	/**
	 * A state machine is a classifier
	 * 
	 */
	declare @type: @StateMachine *: @Classifier;
	
	after(): staticinitialization(DeclaredStateMachine) {
		DeclaredStateMachine.registry.addBuilder(new StateMachineBuilder());
	}

	static privileged aspect PerStateMachine pertypewithin(@org.umlatj.sm.StateMachine *) {

		DeclaredStateMachine rtStateMachine;

		after() : staticinitialization(@org.umlatj.sm.StateMachine *) {
			this.rtStateMachine = DeclaredStateMachine.get(thisJoinPoint.getSignature()
			        .getDeclaringType());
		}

		pointcut transition(Object self): execution(@org.umlatj.sm.Transition * *.*()) && this(self);

		before(Object self) : transition(self) {
			this.rtStateMachine.getTransition(thisJoinPointStaticPart.getSignature().getName())
			        .before(self);
		}

		after(Object self) returning : transition(self) {
			this.rtStateMachine.getTransition(thisJoinPointStaticPart.getSignature().getName())
			        .afterReturning(self, null);
		}

		after(Object self) throwing : transition(self) {
			this.rtStateMachine.getTransition(thisJoinPointStaticPart.getSignature().getName())
			        .afterThrowing(self, null);
		}

	}

}
