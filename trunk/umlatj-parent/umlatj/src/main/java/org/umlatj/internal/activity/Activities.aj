package org.umlatj.internal.activity;

import org.umlatj.internal.behavior.KBehavior;
import org.umlatj.internal.behavior.Behaviors;

public privileged aspect Activities {
	
	declare @type: @org.umlatj.activity.Activity * : @org.umlatj.kernel.Classifier;

	declare parents:
		(@org.umlatj.activity.Activity *) implements Behaviors.BehaviorAdapter;

	after(): staticinitialization(KBehavior) {
		KBehavior.registry.addBuilder(new ActivityBuilder());
	}
	
	public static privileged aspect PerActivity extends Behaviors.PerBehavior 
		pertypewithin(@org.umlatj.activity.Activity *) {

	}
}
