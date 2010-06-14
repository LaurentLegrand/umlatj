package org.umlatj.internal.behavior;

public aspect OpaqueBehaviors {

	declare parents:
		(@org.umlatj.behavior.OpaqueBehavior *) implements Behaviors.BehaviorAdapter;

	after(): staticinitialization(KBehavior) {
		KBehavior.registry.addBuilder(new OpaqueBehaviorBuilder());
	}
	
	public static privileged aspect PerOpaqueBehavior extends 
	Behaviors.PerBehavior 
		pertypewithin(@org.umlatj.behavior.OpaqueBehavior *) {

	}
}
