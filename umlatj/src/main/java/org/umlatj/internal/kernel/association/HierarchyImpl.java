package org.umlatj.internal.kernel.association;

import java.util.ArrayList;
import java.util.List;

import org.umlatj.internal.kernel.KAssociation;
import org.umlatj.kernel.Association.Hierarchy;

public class HierarchyImpl<E> extends KAssociation<E, E> implements
		Hierarchy<E> {

	public HierarchyImpl(String name) {
		super(name);
	}

	// @Override
	public List<E> getAncestors(E self) {
		List<E> list = new ArrayList<E>();
		E parent = this.getParent(self);
		while (parent != null) {
			list.add(0, parent);
			parent = this.getParent(parent);
		}
		return list;
	}

	// @Override
	public E getParent(E self) {
		List<E> list = this.getTarget().toList(self);
		return (list.isEmpty()) ? null : list.get(0);
	}

	// @Override
	public E getRoot(E self) {
		E parent = this.getParent(self);
		while (parent != null) {
			self = parent;
			parent = this.getParent(self);
		}
		return self;
	}

	public List<E> getAncestorsOrSelf(E self) {
		List<E> list = this.getAncestors(self);
		list.add(self);
		return list;
	}

	// @Override
	public List<E> getChildren(E self) {
		return this.getTarget().toList(self);
	}

	// @Override
	public List<E> getDescendants(E self) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public List<E> getDescendantsOrSelf(E self) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public List<E> getFollowingSiblings(E self) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public List<E> getFollowings(E self) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public List<E> getPrecedingSiblings(E self) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public List<E> getPrecedings(E self) {
		// TODO Auto-generated method stub
		return null;
	}

}
