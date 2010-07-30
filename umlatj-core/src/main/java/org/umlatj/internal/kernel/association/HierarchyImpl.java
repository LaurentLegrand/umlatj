//
// uml@J, UML annotations for Java
//
// Copyright (C) 2010 Laurent Legrand or third-party contributors as
// indicated by the @author tags or express copyright attribution
// statements applied by the authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
//

package org.umlatj.internal.kernel.association;

import java.util.ArrayList;
import java.util.Collections;
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
		return this.getOwner().toList(self);
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
		E parent = this.getParent(self);
		if (parent == null) {
			return Collections.emptyList();
		}
		List<E> siblings = this.getChildren(parent);
		return siblings.subList(siblings.indexOf(self) + 1, siblings.size());
	}

	// @Override
	public List<E> getFollowings(E self) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> getPrecedingSiblings(E self) {
		E parent = this.getParent(self);
		if (parent == null) {
			return Collections.emptyList();
		}
		List<E> siblings = this.getChildren(parent);
		return siblings.subList(0, siblings.indexOf(self));
	}

	// @Override
	public List<E> getPrecedings(E self) {
		// TODO Auto-generated method stub
		return null;
	}

}
