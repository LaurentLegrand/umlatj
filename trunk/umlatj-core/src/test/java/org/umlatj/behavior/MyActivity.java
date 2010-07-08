package org.umlatj.behavior;

import org.umlatj.action.Action;
import org.umlatj.activity.Activity;
import org.umlatj.kernel.Constraint;

@Activity
public class MyActivity {
	
	boolean called;

	@Action
	public MyOpaqueBehavior a() {
		return new MyOpaqueBehavior();
	}

	protected void a(MyOpaqueBehavior myOpaqueBehavior) {
		this.called = true;
	}

	@Action(preConditions = "test1 && test2")
	public void doSthg() {

	}

	@Constraint
	public boolean isTest1() {
		return true;
	}

	@Constraint
	public boolean isTest2() {
		return true;
	}

}
