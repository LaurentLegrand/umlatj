package org.umlatj.kernel;

import org.junit.Assert;
import org.junit.Test;
import org.umlatj.internal.kernel.KClassifier;
import org.umlatj.internal.reflect.Types;

public class ClassifierTest {

	@Classifier
	class MyClassifier {

		@Property
		String name;

		String test;

		@Property
		public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
		}

	}

	/**
	 * Test if the properties are found on a fields and on getter methods.
	 */
	@Test
	public void findProperties() {
		KClassifier classifier = Types.asClassifier(MyClassifier.class);
		Assert.assertNotNull(classifier);

		Assert.assertNotNull(classifier.getAttribute("name"));
		Assert.assertNotNull(classifier.getAttribute("test"));
	}

	@Test
	public void verifyAspect() {
		MyClassifier classifier = new MyClassifier();
		classifier.test = "test";
		Assert.assertEquals("test", classifier.getTest());

	}

}
