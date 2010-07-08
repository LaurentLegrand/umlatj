package org.umlatj.profile.basic;

import org.umlatj.action.Action;
import org.umlatj.kernel.Constraint;
import org.umlatj.profile.behavior.basic.Basic;
import org.umlatj.profile.behavior.basic.Goto;
import org.umlatj.profile.behavior.basic.If;

@Basic("a")
public class MyBasic {

	@Goto("b")
	public void a() {
	}

	@If(condition = "C", then = "c", otherwise = "d")
	public void b() {

	}

	@Action
	public void c() {

	}

	@Action
	public void d() {

	}

	@Constraint
	public boolean isC() {
		return true;
	}

}
