package wiki;

import java.util.ArrayList;
import java.util.Collection;

import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Property;

@Classifier
public class Element {
	
	/**
	 * Declare the property ownedElement as a derived union.
	 * 
	 * @return
	 */
	@Property(isDerivedUnion = true)
	public Collection<Element> getOwnedElement() {
		return null;
	}
	
	/**
	 * Declare the property ownedComment as a subset of ownedElement
	 */
	@Property(subset="ownedElement")
	Collection<Comment> ownedComment = new ArrayList<Comment>();
	
}
