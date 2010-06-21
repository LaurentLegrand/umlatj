package wiki;

import org.junit.Assert;
import org.junit.Test;

public class DerivedUnionTest {
	
	@Test
	public void test() {
		Element element = new Element();
		Comment comment = new Comment();
		
		// add the comment 
		element.ownedComment.add(comment);
		
		// verify that the comment is contained in the ownedElement property
		Assert.assertTrue(element.getOwnedElement().contains(comment));
	}

}
