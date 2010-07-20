package org.umlatj.kernel;

import org.junit.Assert;
import org.junit.Test;

public class Issue5Test {

	@Test
	public void test() {
		Issue5 issue5 = new Issue5();
		issue5.subset = "";
		Assert.assertNotNull(issue5.getDerivedUnion());
	}

}
