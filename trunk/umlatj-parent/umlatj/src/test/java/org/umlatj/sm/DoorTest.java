package org.umlatj.sm;


import org.junit.Assert;
import org.junit.Test;
import org.umlatj.internal.sm.DeclaredStateMachine;
import org.umlatj.sm.Door.DoorState;

public class DoorTest {

	@Test
	public void close() {
		Door door = new Door();
		door.close();
		Assert.assertTrue(door.state == DoorState.CLOSED);
	}

	@Test
	public void closeAndLock() {
		Door door = new Door();
		door.close();
		door.lock();
		Assert.assertTrue(door.state == DoorState.LOCKED);
	}

	@Test
	public void closeAndLockAndUnlock() {
		Door door = new Door();
		door.close();
		door.lock();
		door.unlock();
		Assert.assertTrue(door.state == DoorState.CLOSED);
	}

	@Test
	public void closeAndLockAndUnlockAndOpen() {
		Door door = new Door();
		door.close();
		door.lock();
		door.unlock();
		door.open();
		Assert.assertTrue(door.state == DoorState.OPENED);
	}

	public void testReflect() {
		Door door = new Door();
		DeclaredStateMachine stateMachine = DeclaredStateMachine.get(Door.class);
		Assert.assertEquals(stateMachine.getCurrentState(door), DoorState.OPENED);
	}

}
