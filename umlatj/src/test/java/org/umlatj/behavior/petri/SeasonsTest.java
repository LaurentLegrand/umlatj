package org.umlatj.behavior.petri;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.umlatj.internal.behavior.petri.RtInputArc;
import org.umlatj.internal.behavior.petri.RtNet;
import org.umlatj.internal.behavior.petri.RtOutputArc;
import org.umlatj.internal.behavior.petri.RtPlace;
import org.umlatj.internal.behavior.petri.RtTransition;
import org.umlatj.internal.behavior.petri.RtNet.NetInstance;

public class SeasonsTest {

	static RtNet net = new RtNet();

	static RtPlace winter = new RtPlace("winter", (short) 1);

	static RtPlace spring = new RtPlace("spring");

	static RtPlace summer = new RtPlace("summer");

	static RtPlace autumn = new RtPlace("autumn");

	static RtTransition winterSolstice = new RtTransition("winterSolstice");

	static RtTransition springEquinox = new RtTransition("vernalEquinox");

	static RtTransition autumnalEquinox = new RtTransition("autumnalEquinox");

	static RtTransition summerSolstice = new RtTransition("summerSolstice");

	@BeforeClass
	public static void before() {
		net.addPlace(winter);
		net.addPlace(spring);
		net.addPlace(summer);
		net.addPlace(autumn);

		net.addTransition(winterSolstice);
		net.addTransition(springEquinox);
		net.addTransition(summerSolstice);
		net.addTransition(autumnalEquinox);

		winterSolstice.addInputArc(new RtInputArc(autumn));
		winterSolstice.addOutputArc(new RtOutputArc(winter));

		springEquinox.addInputArc(new RtInputArc(winter));
		springEquinox.addOutputArc(new RtOutputArc(spring));

		summerSolstice.addInputArc(new RtInputArc(spring));
		summerSolstice.addOutputArc(new RtOutputArc(summer));

		autumnalEquinox.addInputArc(new RtInputArc(summer));
		autumnalEquinox.addOutputArc(new RtOutputArc(autumn));
	}

	@Test
	public void testInit() {
		NetInstance instance = net.newInstance();
		Assert.assertTrue(springEquinox.isEnabled(instance));
		Assert.assertFalse(summerSolstice.isEnabled(instance));
		Assert.assertFalse(autumnalEquinox.isEnabled(instance));
		Assert.assertFalse(winterSolstice.isEnabled(instance));
		
		Assert.assertFalse(net.isDead(instance));
	}
	
	@Test
	public void testSpring() {
		NetInstance instance = net.newInstance();
		Assert.assertTrue(springEquinox.isEnabled(instance));
		springEquinox.fire(instance);
		Assert.assertFalse(winter.contains(instance));
		Assert.assertFalse(springEquinox.isEnabled(instance));
		Assert.assertTrue(spring.contains(instance));
	}

}
