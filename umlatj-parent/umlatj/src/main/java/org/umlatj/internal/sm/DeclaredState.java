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
