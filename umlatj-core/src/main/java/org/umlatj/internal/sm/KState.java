package org.umlatj.internal.sm;

import java.util.ArrayList;
import java.util.List;

import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Property;

@Classifier
public class KState {

	@Property
	KState parent;

	@Property
	List<KState> children = new ArrayList<KState>();
	
	String name;

	/**
	 * Return <code>true</code> if this state has no children.
	 * 
	 * @return
	 */
	public boolean isAtomic() {
		return this.children.isEmpty();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KState getParent() {
		return parent;
	}

	public List<KState> getChildren() {
		return children;
	}
}
