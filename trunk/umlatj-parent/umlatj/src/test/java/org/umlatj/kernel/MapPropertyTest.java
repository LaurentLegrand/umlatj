package org.umlatj.kernel;

import java.util.HashMap;
import java.util.Map;


import org.junit.Assert;
import org.junit.Test;
import org.umlatj.internal.kernel.KClassifier;
import org.umlatj.internal.kernel.property.MapProperty;
import org.umlatj.internal.reflect.Types;
import org.umlatj.kernel.Association;
import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Package;
import org.umlatj.kernel.Property;
import org.umlatj.kernel.Association.Binary;
import org.umlatj.kernel.Association.End;

public class MapPropertyTest {

	@Package
	static class MyPackage {

		@Association( { @End("parent"), @End("children") })
		private static Binary<MapClassifier, MapClassifier> hierarchy;
	}

	@Classifier
	static class MapClassifier {

		@Property
		MapClassifier parent;

		@Property(qualifier = "name")
		Map<String, MapClassifier> children = new HashMap<String, MapClassifier>();

		@Property
		public String name;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMapProperty() {
		KClassifier classifier = Types.asClassifier(MapClassifier.class);
		Assert.assertTrue(classifier.getAttribute("children") instanceof MapProperty);
	}

	@Test
	public void testAddHierarchy() {
		MapClassifier parent = new MapClassifier();
		parent.name = "parent";

		MapClassifier child = new MapClassifier();
		child.name = "child";

		MyPackage.hierarchy.add(parent, child);

		Assert.assertTrue(parent.children.containsKey("child"));
	}

}
