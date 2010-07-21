package org.umlatj.internal.sm;

public class KStateMachine {

	/**
	 * The root of all states
	 */
	KState root = new KState();

	public KStateMachine() {
		this.root.setName("<root>");
	}

}
