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

package org.umlatj.internal.sm;

import org.umlatj.internal.kernel.KNamedElement;
import org.umlatj.internal.util.aop.Advice;
import org.umlatj.sm.StateMachine;
import org.umlatj.sm.Transition;


/**
 * Runtime counterpart of {@link Transition}.
 * 
 * The firing of the transition is decomposed in two steps:
 * {@link #preFire(Object)} and {@link #postFire(Object)}. It is up to the sub
 * class to call them in this order.
 * 
 * @author Laurent Legrand
 * @version $Id$
 * 
 * @param <M>
 *            the class annotated with {@link StateMachine}.
 * @param <S>
 *            the enumeration containing the list of available states.
 */
public class DeclaredTransition extends KNamedElement implements Advice {

	/**
	 * The machine this transition belongs to
	 */
	DeclaredStateMachine stateMachine;

	/**
	 * The source state
	 */
	private DeclaredState source;

	/**
	 * The target state
	 */
	private DeclaredState target;

	public DeclaredTransition(String name) {
		super(name);
	}

	/**
	 * Verify if this transition is enabled: if the machine is in the source
	 * state.
	 * 
	 * @param self
	 *            the instance of the machine.
	 * @return <code>true</code> if the transition can be fired
	 */
	public boolean isEnabled(Object self) {
		return this.stateMachine.verifyCurrentState(self, this.source);
	}

	/**
	 * Perform the pre firing of the transition: state value verification and
	 * exit of the source state
	 * 
	 * @param self
	 *            the instance of the machine.
	 * @throws IllegalStateException
	 *             when the machine is not in the good state (
	 *             {@link #isEnabled(Object)}
	 */
	public void before(Object self, Object... args) throws IllegalStateException {
		if (!this.isEnabled(self)) {
			throw new IllegalStateException(String.format(
			        "Machine %s expected state: %s found: %s", self, this.source.getState(),
			        this.stateMachine.getCurrentState(self)));
		}
		if (this.source != this.target) {
			this.source.exit(self);
		}
	}

	/**
	 * Perform the post firing of the transition: exit of the target state
	 * 
	 * @param self
	 */
	public void afterReturning(Object self, Object returning, Object... args) {
		if (this.source != this.target) {
			this.target.enter(self);
		}
	}

	public void afterThrowing(Object self, Exception e, Object... args) {
		this.source.enter(self);
	}

	/*
	 * public void fire(M self) throws IllegalStateException { if
	 * (!this.isEnabled(self)) { throw new IllegalStateException(String.format(
	 * "Machine %s expected state: %s found: %s", self, this.source.getState(),
	 * this.machineImpl.getCurrent(self))); } // call exit states if
	 * (this.source != this.target) { this.source.exit(self); } try {
	 * this.proceed(self); if (this.source != this.target) {
	 * this.target.enter(self); } } catch (RuntimeException e) { if (this.source
	 * != this.target) { this.source.enter(self); } throw e; } }
	 */

	// protected abstract void proceed(M self) throws RuntimeException;
	void setStateMachine(DeclaredStateMachine machineImpl) {
		this.stateMachine = machineImpl;
	}

	public DeclaredStateMachine getStateMachine() {
		return stateMachine;
	}

	public void setSource(DeclaredState source) {
		this.source = source;
	}

	public DeclaredState getSource() {
		return source;
	}

	public void setTarget(DeclaredState target) {
		this.target = target;
	}

	public DeclaredState getTarget() {
		return target;
	}

}
