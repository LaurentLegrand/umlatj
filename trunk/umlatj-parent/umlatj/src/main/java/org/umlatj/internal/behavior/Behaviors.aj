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

package org.umlatj.internal.behavior;

import org.umlatj.internal.behavior.KBehavior;
import org.umlatj.internal.behavior.KBehavior.BehaviorInstance;

public aspect Behaviors {
	
	/**
	 * 
	 * 
	 * @author Laurent Legrand
	 *
	 */
	public static interface BehaviorAdapter {
		
	}

	BehaviorInstance BehaviorAdapter.instance;
	
	Object around(Object self): execution(* KBehavior.adapt(Object)) && args(self) {
		return (self instanceof BehaviorAdapter) ? ((BehaviorAdapter)self).instance : null;
	}
	
	public static abstract privileged aspect PerBehavior {
		//pertypewithin(@(org.umlatj.behavior.OpaqueBehavior || org.umlatj.behavior.Activity) *) {

		KBehavior behavior;
		
		after() : staticinitialization(*) {
			this.behavior = KBehavior.get(thisJoinPoint.getSignature().getDeclaringType());
		}
		
		after (Object self): execution(*.new(..)) && this(self) {
			((BehaviorAdapter)self).instance = this.behavior.newInstance(self);
		}
	}

	/*static privileged aspect PerOpaqueBehavior pertypewithin(@org.umlatj.behavior.OpaqueBehavior *) {

		RtOpaqueBehavior behavior;

		after() : staticinitialization(*) {
			this.behavior = RtOpaqueBehavior.get(thisJoinPoint.getSignature().getDeclaringType());
		}
		
		after (Object self): execution(*.new(..)) && this(self) {
			((BehaviorAdapter)self).instance = this.behavior.newInstance(self);
			System.out.println("PerOpaqueBehavior intertype done");
		}
		
	}*/

	/*static privileged aspect PerActivity pertypewithin(@org.umlatj.behavior.Activity *) {

		RtActivity activity;

		after() : staticinitialization(@org.umlatj.behavior.Activity *) {
			this.activity = RtActivity.get(thisJoinPoint.getSignature().getDeclaringType());
		}
		
		after (Object self): execution((@org.umlatj.behavior.Activity *).new(..)) && this(self) {
			Object instance = this.activity.newInstance(self);
			((BehaviorAdapter)self).instance = (BehaviorInstance)instance;
			System.out.println("intertype done");
		}
		
	}*/
}
