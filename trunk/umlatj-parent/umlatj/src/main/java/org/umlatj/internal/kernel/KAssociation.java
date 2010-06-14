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

package org.umlatj.internal.kernel;

import org.umlatj.kernel.Association.Binary;

/**
 * Runtime part of a binary association.
 * 
 * 
 * 
 * @author Laurent Legrand
 * 
 * @param <O>
 *            the owner of the association corresponding to the first end
 * @param <T>
 *            the target of the association corresponding to the second end
 */
public class KAssociation<O, T> extends KNamedElement implements Binary<O, T> {

	private KPackage rtPackage;

	private KProperty<T> owner;

	private KProperty<O> target;

	public KAssociation(String name) {
		super(name);
	}

	public boolean add(O owner, T target) {
		if (owner == null || target == null) {
			return false;
		}
		// do some clean-up before
		this.remove(owner, this.owner.evict(owner));
		this.remove(this.target.evict(target), target);

		this.owner.proceedAdd(owner, target);
		this.target.proceedAdd(target, owner);
		return true;
	}

	public boolean remove(O owner, T target) {
		if (owner == null || target == null) {
			return false;
		}
		this.owner.proceedRemove(owner, target);
		this.target.proceedRemove(target, owner);
		return true;
	}

	public boolean exists(O owner, T target) {
		return this.owner.contains(owner, target);
	}

	public KProperty<T> getOwner() {
		return owner;
	}

	public void setOwner(KProperty<T> owner) {
		this.owner = owner;
		this.owner.setAssociationAsOwner(this);
	}

	public void setTarget(KProperty<O> target) {
		this.target = target;
		this.target.setAssociationAsTarget(this);
	}

	public KProperty<O> getTarget() {
		return target;
	}

	public KPackage getPackage() {
		return rtPackage;
	}

	public void setPackage(KPackage rtPackage) {
		this.rtPackage = rtPackage;
	}

}
