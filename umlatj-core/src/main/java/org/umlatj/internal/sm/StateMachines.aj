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