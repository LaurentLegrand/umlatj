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
