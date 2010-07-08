package org.umlatj.kernel;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.umlatj.internal.kernel.KAssociation;
import org.umlatj.internal.kernel.property.CollectionProperty;
import org.umlatj.internal.kernel.property.SingletonProperty;

public class OneToManyTest {

	static Many<Long> owner = new Many<Long>();

	static One<String> target = new One<String>();

	static CollectionProperty<Long> ownerProperty = new CollectionProperty<Long>(
	        owner);

	static SingletonProperty<String> targetProperty = new SingletonProperty<String>(
	        target);


	static KAssociation<String, Long> association;

	@BeforeClass
	public static void init() {
		association = new KAssociation<String, Long>("");
		association.setOwner(ownerProperty);
		association.setTarget(targetProperty);
	}

	@Before
	public void reset() {
		owner.getMap().clear();
		target.getMap().clear();
	}

	@Test
	public void testSet() {
		association.add("a", 1L);
		Assert.assertTrue(owner.get("a").contains(1L));
		Assert.assertTrue(target.get(1L).equals("a"));
	}

	@Test
	public void testTwoSet() {
		association.add("a", 1L);
		association.add("a", 2L);
		Assert.assertTrue(owner.get("a").contains(1L));
		Assert.assertTrue(owner.get("a").contains(2L));
		Assert.assertTrue(target.get(1L).equals("a"));
		Assert.assertTrue(target.get(2L).equals("a"));
	}

}
