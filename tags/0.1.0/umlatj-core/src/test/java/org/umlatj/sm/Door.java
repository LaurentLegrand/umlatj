package org.umlatj.sm;

import org.umlatj.sm.State;
import org.umlatj.sm.StateMachine;
import org.umlatj.sm.Transition;

@StateMachine
public class Door {

	enum DoorState {
		OPENED, CLOSED, LOCKED;
	}

	@State
	DoorState state = DoorState.OPENED;

	@Transition(source = "OPENED", target = "CLOSED")
	public void close() {

	}

	@Transition(source = "CLOSED", target = "OPENED")
	public void open() {

	}

	@Transition(source = "CLOSED", target = "LOCKED")
	public void lock() {

	}

	@Transition(source = "LOCKED", target = "CLOSED")
	public void unlock() {

	}
}
