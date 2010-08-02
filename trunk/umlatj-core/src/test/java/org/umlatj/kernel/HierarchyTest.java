package org.umlatj.kernel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.umlatj.internal.kernel.association.HierarchyImpl;
import org.umlatj.internal.kernel.property.CollectionProperty;
import org.umlatj.internal.kernel.property.SingletonProperty;
import org.umlatj.kernel.Association.End;
import org.umlatj.kernel.Association.Hierarchy;

public class HierarchyTest {

	@Package
	static class MyPackage {

		@Association({ @End("parent"), @End("children") })
		static Hierarchy<MyNode> hierarchy;

		public static Hierarchy<MyNode> getHierarchy() {
			return hierarchy;
		}
	}

	@Classifier
	static class MyNode {

		@Property
		MyNode parent;

		@Property
		List<MyNode> children = new ArrayList<MyNode>();
	}

	static Many<String> owner = new Many<String>();

	static One<String> target = new One<String>();

	static CollectionProperty<String> ownerProperty = new CollectionProperty<String>(
			owner);

	static SingletonProperty<String> targetProperty = new SingletonProperty<String>(
			target);

	static HierarchyImpl<String> hierarchy;

	@BeforeClass
	public static void init() {
		hierarchy = new HierarchyImpl<String>("");
		hierarchy.setOwner(ownerProperty);
		hierarchy.setTarget(targetProperty);
	}

	@Before
	public void reset() {
		owner.getMap().clear();
		target.getMap().clear();
	}

	@Test
	public void testSet() {
		hierarchy.add("1", "1.1");
		Assert.assertTrue(owner.get("1").contains("1.1"));
		Assert.assertTrue(target.get("1.1").equals("1"));
	}

	@Test
	public void testTwoSet() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1", "1.2");
		Assert.assertTrue(owner.get("1").contains("1.1"));
		Assert.assertTrue(owner.get("1").contains("1.2"));
		Assert.assertTrue(target.get("1.1").equals("1"));
		Assert.assertTrue(target.get("1.2").equals("1"));
	}

	@Test
	public void testParent() {
		hierarchy.add("1", "1.1");
		Assert.assertEquals("1", hierarchy.getParent("1.1"));
		Assert.assertNull(hierarchy.getParent("1"));
	}

	@Test
	public void testRoot() {
		hierarchy.add("1", "1.1");
		Assert.assertEquals("1", hierarchy.getRoot("1.1"));
		Assert.assertEquals("1", hierarchy.getRoot("1"));
	}

	@Test
	public void testAncestors() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1.1", "1.1.1");
		List<String> ancestors = hierarchy.getAncestors("1.1.1");
		Assert.assertEquals(ancestors.size(), 2);
		Assert.assertEquals("1.1", ancestors.get(1));
		Assert.assertEquals("1", ancestors.get(0));
	}

	@Test
	public void testAncestorsOrSelf() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1.1", "1.1.1");
		List<String> ancestors = hierarchy.getAncestorsOrSelf("1.1.1");
		Assert.assertEquals(ancestors.size(), 3);
		Assert.assertEquals("1", ancestors.get(0));
		Assert.assertEquals("1.1", ancestors.get(1));
		Assert.assertEquals("1.1.1", ancestors.get(2));
	}

	@Test
	public void testNode() {
		MyNode parent = new MyNode();
		MyNode child = new MyNode();

		MyPackage.getHierarchy().add(parent, child);
		Assert.assertEquals(parent, child.parent);
		Assert.assertEquals(parent, MyPackage.getHierarchy().getParent(child));
		Assert.assertEquals(parent, MyPackage.getHierarchy().getRoot(child));
	}

	@Test
	public void testChildren() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1", "1.2");
		hierarchy.add("1", "1.3");
		hierarchy.add("1", "1.4");

		Assert.assertEquals(hierarchy.getChildren("1").size(), 4);
		Assert.assertTrue(hierarchy.getChildren("1.4").isEmpty());
	}

	@Test
	public void testFollowingSibling() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1", "1.2");
		hierarchy.add("1", "1.3");
		hierarchy.add("1", "1.4");

		Assert.assertTrue(hierarchy.getFollowingSiblings("1").isEmpty());
		Assert.assertTrue(hierarchy.getFollowingSiblings("1.4").isEmpty());
		Assert.assertTrue(hierarchy.getFollowingSiblings("1.3").size() == 1);
		Assert.assertTrue(hierarchy.getFollowingSiblings("1.3").contains("1.4"));
	}

	@Test
	public void testPrecedingSibling() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1", "1.2");
		hierarchy.add("1", "1.3");
		hierarchy.add("1", "1.4");

		Assert.assertTrue(hierarchy.getPrecedingSiblings("1").isEmpty());
		Assert.assertTrue(hierarchy.getPrecedingSiblings("1.1").isEmpty());
		Assert.assertTrue(hierarchy.getPrecedingSiblings("1.2").size() == 1);
		Assert.assertTrue(hierarchy.getPrecedingSiblings("1.2").contains("1.1"));
	}

	@Test
	public void testDescendants() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1.1", "1.1.1");
		hierarchy.add("1.1.1", "1.1.1.1");
		hierarchy.add("1", "1.2");

		Assert.assertTrue(hierarchy.getDescendants("1.1").size() == 2);
		Assert.assertTrue(hierarchy.getDescendants("1.1").contains("1.1.1"));
		Assert.assertTrue(hierarchy.getDescendants("1.1").contains("1.1.1.1"));
		Assert.assertTrue(hierarchy.getDescendants("1.2").isEmpty());
	}
	
	@Test
	public void testDescendantsOrSelf() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1.1", "1.1.1");
		hierarchy.add("1.1.1", "1.1.1.1");
		hierarchy.add("1", "1.2");

		Assert.assertTrue(hierarchy.getDescendantsOrSelf("1.1").size() == 3);
		Assert.assertTrue(hierarchy.getDescendantsOrSelf("1.1").contains("1.1"));
		Assert.assertTrue(hierarchy.getDescendantsOrSelf("1.1").contains("1.1.1"));
		Assert.assertTrue(hierarchy.getDescendantsOrSelf("1.1").contains("1.1.1.1"));
		Assert.assertTrue(hierarchy.getDescendantsOrSelf("1.2").size() == 1);
	}
	
	@Test
	public void testFollowing() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1.1", "1.1.1");
		hierarchy.add("1.1.1", "1.1.1.1");
		hierarchy.add("1", "1.2");
		hierarchy.add("1.2", "1.2.1");
		hierarchy.add("1", "1.3");
		hierarchy.add("1", "1.4");
		
		Assert.assertTrue(hierarchy.getFollowings("1").isEmpty());
		
		Assert.assertTrue(hierarchy.getFollowings("1.1").size() == 4);
		Assert.assertTrue(hierarchy.getFollowings("1.1").contains("1.2"));
		Assert.assertTrue(hierarchy.getFollowings("1.1").contains("1.2.1"));
		Assert.assertTrue(hierarchy.getFollowings("1.1").contains("1.3"));
		Assert.assertTrue(hierarchy.getFollowings("1.1").contains("1.4"));
		
		Assert.assertTrue(hierarchy.getFollowings("1.4").isEmpty());
	}
	
	@Test
	public void testPreceding() {
		hierarchy.add("1", "1.1");
		hierarchy.add("1.1", "1.1.1");
		hierarchy.add("1.1.1", "1.1.1.1");
		hierarchy.add("1", "1.2");
		hierarchy.add("1.2", "1.2.1");
		hierarchy.add("1", "1.3");
		hierarchy.add("1", "1.4");
		
		Assert.assertTrue(hierarchy.getPrecedings("1").isEmpty());
		
		Assert.assertTrue(hierarchy.getPrecedings("1.4").size() == 6);
		Assert.assertTrue(hierarchy.getPrecedings("1.4").contains("1.1"));
		Assert.assertTrue(hierarchy.getPrecedings("1.4").contains("1.1.1"));
		Assert.assertTrue(hierarchy.getPrecedings("1.4").contains("1.1.1.1"));
		Assert.assertTrue(hierarchy.getPrecedings("1.4").contains("1.2"));
		Assert.assertTrue(hierarchy.getPrecedings("1.4").contains("1.2.1"));
		Assert.assertTrue(hierarchy.getPrecedings("1.4").contains("1.3"));
		
		Assert.assertTrue(hierarchy.getPrecedings("1.1.1").isEmpty());
	}

}
