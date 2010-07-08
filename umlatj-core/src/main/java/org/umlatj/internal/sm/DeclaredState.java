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
import org.umlatj.internal.util.proxy.MethodProxy;
import org.umlatj.sm.StateMachine;


/**
 * Represents one of the available states for a given state machine.
 * 
 * @author Laurent Legrand
 * @version $Id$
 * 
 * @param <M>
 *            the class annotated with {@link StateMachine}.
 * @param <S>
 *            the enumeration containing the list of available states.
 */
public class DeclaredState extends KNamedElement {

	/**
	 * The state machine this state belongs to
	 */
	private DeclaredStateMachine stateMachine;

	MethodProxy<Void> entry;

	MethodProxy<Void> exit;

	/**
	 * The value of this state
	 */
	private final Object state;

	public DeclaredState(Object state) {
		super(state.toString());
		this.state = state;
	}

	public Object getState() {
		return state;
	}

	/**
	 * Enter the machine in this state.
	 * 
	 * @param self
	 *            the instance of the machine
	 */
	public final void enter(Object self) {

		if (this.stateMachine.getCurrentState(self) == this.state) {
			return;
		}
		this.stateMachine.setCurrentState(self, this.state);
		this.entered(self);

	}

	/**
	 * Exit the machine from this state.
	 * 
	 * @param self
	 *            the instance of the machine
	 */
	public final void exit(Object self) {
		this.stateMachine.setCurrentState(self, null);
		this.exited(self);
	}

	/**
	 * Call back executed just after the machine has entered this state.
	 * 
	 * This method must not be called directly.
	 * 
	 * @param self
	 *            the instance of the machine
	 */
	protected void entered(Object self) {
		if (this.entry != null) {
			this.entry.invoke(self);
		}
	}

	/**
	 * Call back executed just after the machine has exited this state
	 * 
	 * This method must not be called directly.
	 * 
	 * @param self
	 *            the instance of the machine
	 */
	protected void exited(Object self) {
		if (this.exit != null) {
			this.exit.invoke(self);
		}
	}

	void setStateMachine(DeclaredStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	public DeclaredStateMachine getStateMachine() {
		return stateMachine;
	}

	public MethodProxy<Void> getEntry() {
		return entry;
	}

	public void setEntry(MethodProxy<Void> entry) {
		this.entry = entry;
	}

	public MethodProxy<Void> getExit() {
		return exit;
	}

	public void setExit(MethodProxy<Void> exit) {
		this.exit = exit;
	}
}
