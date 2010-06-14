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
