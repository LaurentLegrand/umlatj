package org.umlatj.internal.sm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Property;

@Classifier
public class KState {

	@Property
	KState parent;

	@Property
	List<KState> children = new ArrayList<KState>();

	/**
	 * 
	 */
	String name;

	/**
	 * Incoming transitions
	 * 
	 */
	@Property
	Set<KTransition> incoming = new HashSet<KTransition>();

	/**
	 * Outgoing transitions
	 */
	@Property
	Set<KTransition> outgoing = new HashSet<KTransition>();

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
