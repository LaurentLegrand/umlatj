package org.umlatj.internal.profile.behavior.basic;

import org.umlatj.internal.behavior.KBehavior;
import org.umlatj.internal.behavior.Behaviors;

public privileged aspect Basics {

	/**
	 * 
	 */
	declare parents:
		(@org.umlatj.profile.behavior.basic.Basic *) implements Behaviors.BehaviorAdapter;

	/**
	 * 
	 */
	declare @type:
		@org.umlatj.profile.behavior.basic.Basic * : @org.umlatj.kernel.Classifier;

	/**
	 * 
	 */
	declare @method:
		@org.umlatj.profile.behavior.basic.Goto * *.*(..) : @org.umlatj.action.Action;
	
	/**
	 * 
	 */
	after(): staticinitialization(KBehavior) {
		KBehavior.registry.addBuilder(new BasicBuilder());
	}
	
	public static privileged aspect PerBasic extends Behaviors.PerBehavior 
		pertypewithin(@org.umlatj.profile.behavior.basic.Basic *) {
	}
}
