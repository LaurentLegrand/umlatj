//
// uml@J, UML annotations for Java
//
// Copyright (C) 2010 Laurent Legrand or third-party contributors as
// indicated by the @author tags or express copyright attribution
// statements applied by the authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
//

package org.umlatj.internal.behavior;

import org.umlatj.internal.behavior.KBehavior.BehaviorInstance;
import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.kernel.KNamedElement;
import org.umlatj.internal.util.aop.Advice;
import org.umlatj.internal.util.proxy.MethodProxy;
import org.umlatj.petri.Transition;


public abstract class KAction extends KNamedElement implements Advice {

	KBehavior behavior;

	MethodProxy<?> method;

	KConstraint preCondition;

	KConstraint postCondition;

	Transition preTransition; //= new Transition("pre-" + this.getName());

	Transition postTransition; //= new Transition("post-" + this.getName());

	public KAction(MethodProxy<?> method) {
		super(method.getName());
		this.method = method;
	}

	public boolean isEnabled(Object self) {
		if (this.preCondition != null) {
			if (!this.preCondition.verify(self)) {
				return false;
			}
		}
		return true;
	}

	public void before(Object self, Object... args) throws IllegalStateException {
		BehaviorInstance instance = KBehavior.adapt(self);
		if (!this.preTransition.isEnabled(instance.marking)) {
			throw new IllegalStateException("Pre conditions not met");
		}
		if (!this.isEnabled(instance)) {
			throw new IllegalStateException("Pre conditions not met");
		}
		this.preTransition.fire(instance.marking);
	}

	public void afterReturning(Object self, Object returning, Object... args) {
		BehaviorInstance instance = KBehavior.adapt(self);
		if (this.postCondition != null) {
			if (!this.postCondition.verify(self)) {
				throw new IllegalStateException("Post conditions not met");
			}
		}
		this.postTransition.fire(instance.marking);
	}

	public void afterThrowing(Object self, Exception e, Object... args) {

	}

	public KConstraint getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(KConstraint preCondition) {
		this.preCondition = preCondition;
	}

	public KConstraint getPostCondition() {
		return postCondition;
	}

	public void setPostCondition(KConstraint postCondition) {
		this.postCondition = postCondition;
	}

	/**
	 * @return the preTransition
	 */
	public Transition getPreTransition() {
		return preTransition;
	}

	/**
	 * @return the postTransition
	 */
	public Transition getPostTransition() {
		return postTransition;
	}

	/**
	 * @return the behavior
	 */
	public KBehavior getBehavior() {
		return behavior;
	}

	/**
	 * @param behavior
	 *            the behavior to set
	 */
	public void setBehavior(KBehavior behavior) {
		this.behavior = behavior;
//		this.preTransition = 
//		Petri.link(this.behavior.getNet(), this.preTransition);
//		Petri.link(this.behavior.getNet(), this.postTransition);
	}

}
