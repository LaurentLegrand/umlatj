package org.umlatj.kernel;


import org.junit.Assert;
import org.junit.Test;
import org.umlatj.internal.reflect.Types;

public class ClassifierTest {

	@Classifier
	class MyClassifier {

	}

	@Test
	public void test() {
		new MyClassifier();
		Assert.assertNotNull(Types.asClassifier(MyClassifier.class));
	}

}
