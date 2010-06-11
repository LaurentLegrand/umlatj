package org.umlatj.kernel;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.umlatj.internal.kernel.KAssociation;
import org.umlatj.internal.kernel.property.SingletonProperty;

public class OneToOneTest {

	static One<String> owner = new One<String>();

	static One<String> target = new One<String>();

	static SingletonProperty<String> ownerProperty = new SingletonProperty<String>(
	        owner);

	static SingletonProperty<String> targetProperty = new SingletonProperty<String>(
	        target);

	static KAssociation<String, String> association;

	@BeforeClass
	public static void init() {
		association = new KAssociation<String, String>("");
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
		association.add("a", "b");
		Assert.assertTrue(owner.get("a").equals("b"));
		Assert.assertTrue(target.get("b").equals("a"));
	}

	@Test
	public void testUnset() {
		association.add("a", "b");
		association.remove("a", "b");
		Assert.assertNull(owner.get("a"));
		Assert.assertNull(target.get("b"));
	}

	@Test
	public void testSetUnset() {
		association.add("a", "b");
		association.add("a", "c");
		Assert.assertTrue(owner.get("a").equals("c"));
		Assert.assertTrue(target.get("c").equals("a"));
		Assert.assertNull(target.get("b"));
	}

	@Test
	public void testSetThroughProperty() {
		ownerProperty.add("a", "b");
		Assert.assertTrue(owner.get("a").equals("b"));
		Assert.assertTrue(target.get("b").equals("a"));
	}

}
