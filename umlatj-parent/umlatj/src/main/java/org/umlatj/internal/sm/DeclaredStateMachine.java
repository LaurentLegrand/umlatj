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

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.kernel.KType;
import org.umlatj.internal.util.proxy.FieldProxy;
import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.internal.util.registry.Registry;
import org.umlatj.sm.State;
import org.umlatj.sm.StateMachine;



/**
 * Runtime counterpart of {@link StateMachine}
 * 
 * @author Laurent Legrand
 * @version $Id$
 * 
 * @param <M>
 *            the class annotated with {@link StateMachine}.
 * @param <S>
 *            the enumeration containing the list of available states.
 */
public class DeclaredStateMachine extends KType {

	private Map<String, DeclaredState> states = new HashMap<String, DeclaredState>();

	private Map<String, DeclaredTransition> transitions = new HashMap<String, DeclaredTransition>();

	FieldProxy<? super Object> currentStateField;

	public DeclaredStateMachine(TypeProxy type) {
		super(type);
	}

	/**
	 * Get the value of the field annotated with {@link State}
	 * 
	 * @param self
	 *            the instance of the machine
	 * @return the value of the field annotated with {@link State}
	 */
	public Object getCurrentState(Object self) {
		return this.currentStateField.get(self);
	}

	/**
	 * Set the value of the field annotated with {@link State}
	 * 
	 * @param self
	 *            the instance of the machine
	 * @param state
	 *            the new value of the field
	 */
	public void setCurrentState(Object self, Object state) {
		this.currentStateField.set(self, state);
	}

	/**
	 * Verify if a machine is in a given state
	 * 
	 * @param self
	 *            the instance of the machine
	 * @param stateImpl
	 * @return <code>true</code> if the machine is in the given state.
	 *         <code>false</code> otherwise.
	 */
	public boolean verifyCurrentState(Object self, DeclaredState stateImpl) {
		return stateImpl.getState() == this.getCurrentState(self);
	}

	public void addState(DeclaredState state) {
		state.setStateMachine(this);
		this.states.put(state.getName(), state);
	}

	public DeclaredState getState(String name) {
		return this.states.get(name);
	}

	public void addTransition(DeclaredTransition transition) {
		transition.setStateMachine(this);
		this.transitions.put(transition.getName(), transition);
	}

	public DeclaredTransition getTransition(String name) {
		return this.transitions.get(name);
	}

	public FieldProxy<?> getCurrentStateField() {
		return currentStateField;
	}

	@SuppressWarnings("unchecked")
    public void setCurrentStateField(FieldProxy<?> currentStateField) {
		this.currentStateField = (FieldProxy<? super Object>) currentStateField;
	}
	
	static Registry<DeclaredStateMachine> registry = new Registry<DeclaredStateMachine>();

	public static DeclaredStateMachine get(Class<?> type) {
		return registry.get(type);
	}
}
