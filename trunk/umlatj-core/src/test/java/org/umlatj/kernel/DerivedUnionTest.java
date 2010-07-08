package org.umlatj.kernel;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class DerivedUnionTest {

	@Classifier
	public static class General {

		@Property(isDerivedUnion = true)
		public Object getScalar() {
			return null;
		}
		
		@Property(isDerivedUnion = true)
		public Collection<Object> getMyCollection() {
			return null;
		}
	}

	@Classifier
	public static class SubScalar extends General {

		@Property(subset = "scalar")
		Object name;
	}
	
	@Classifier
	public static class SubCollection extends General {

		@Property(subset = "myCollection")
		Collection<String> strings = new ArrayList<String>();
	}
	

	@Test
	public void scalar() {
		SubScalar sub = new SubScalar();
		Assert.assertNull(sub.getScalar());

		sub.name = this;
		Assert.assertEquals(sub.getScalar(), this);

	}
	
	@Test
	public void collection() {
		SubCollection sub = new SubCollection();
		sub.strings.add("a");
		
		Assert.assertTrue(sub.getMyCollection().contains("a"));
	}
	
//	@Test
//	public void dump() throws Exception {
//		BeanInfo info = Introspector.getBeanInfo(Sub.class);
//		
//	}

}
