package org.umlatj.kernel;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.umlatj.internal.kernel.KAssociation;
import org.umlatj.internal.kernel.property.CollectionProperty;

public class ManyToManyTest {

	static Many<Long> owner = new Many<Long>();

	static Many<String> target = new Many<String>();

	static CollectionProperty<Long> ownerProperty = new CollectionProperty<Long>(
	        owner);

	static CollectionProperty<String> targetProperty = new CollectionProperty<String>(
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
		Assert.assertTrue(target.get(1L).contains("a"));
	}

	@Test
	public void testTwoSet() {
		association.add("a", 1L);
		association.add("a", 2L);
		Assert.assertTrue(owner.get("a").contains(1L));
		Assert.assertTrue(owner.get("a").contains(2L));
		Assert.assertTrue(target.get(1L).contains("a"));
		Assert.assertTrue(target.get(2L).contains("a"));
	}

}
