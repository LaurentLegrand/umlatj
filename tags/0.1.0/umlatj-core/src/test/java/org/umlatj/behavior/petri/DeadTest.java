package org.umlatj.behavior.petri;


import org.junit.Assert;
import org.junit.Test;
import org.umlatj.internal.behavior.petri.RtNet;
import org.umlatj.internal.behavior.petri.RtNet.NetInstance;

public class DeadTest {

	static RtNet net = new RtNet();

	@Test
	public void test() {
		NetInstance instance = net.newInstance();
		Assert.assertTrue(net.isDead(instance));
	}
	
}
