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

import java.util.List;

/**
 * Runtime part of a property.
 * 
 * If a property is part of an association, either as owner or target, the
 * property will delegate any operation to that association.
 * 
 * @author Laurent Legrand
 * 
 */
public abstract class KProperty<E> extends KNamedElement {

	/**
	 * Indicates that this property is part of an binary association.
	 * 
	 * It is the self of the association (the first end).
	 */
	private KAssociation<? super Object, E> associationAsOwner;

	/**
	 * Indicates that this property is part of an binary association.
	 * 
	 * It is the target of the association (the second end).
	 */
	private KAssociation<E, ? super Object> associationAsTarget;

	/**
	 * The lower bound of the multiplicity.
	 */
	final int lower;

	/**
	 * The upper bound of the multiplicity.
	 */
	final int upper;

	public KProperty(String name, int lower, int upper) {
		super(name);
		this.lower = lower;
		this.upper = upper;
	}

	/**
	 * Add an element to the property.
	 * 
	 * If this property is part of an association, the property delegates to the
	 * association.
	 * 
	 * @param self
	 * @param element
	 * @return
	 */
	public final boolean add(Object self, E element) {
		if (this.associationAsOwner != null) {
			return this.associationAsOwner.add(self, element);
		}
		if (this.associationAsTarget != null) {
			return this.associationAsTarget.add(element, self);
		}
		return this.proceedAdd(self, element);
	}

	/**
	 * Add an element to the property.
	 * 
	 * If this property is part of an association, the property delegates to the
	 * association.
	 * 
	 * @param self
	 * @param element
	 * @return
	 */
	public final boolean remove(Object self, E element) {
		if (this.associationAsOwner != null) {
			return this.associationAsOwner.remove(self, element);
		}
		if (this.associationAsTarget != null) {
			return this.associationAsTarget.remove(element, self);
		}
		return this.proceedRemove(self, element);
	}

	/**
	 * Perform the add.
	 * 
	 * @param self
	 * @param element
	 * @return
	 */
	protected abstract boolean proceedAdd(Object self, E element);

	/**
	 * Perform the remove.
	 * 
	 * @param self
	 * @param element
	 * @return
	 */
	protected abstract boolean proceedRemove(Object self, E element);

	/**
	 * Verify that an element is contained by this property.
	 * 
	 * @param self
	 * @param element
	 * @return
	 */
	public abstract boolean contains(Object self, E element);

	public boolean isUpperBound() {
		return this.upper != -1;
	}

	/**
	 * For bounded property which are full, it is a way to return an element
	 * that will be removed before an add will be performed.
	 */
	public abstract E evict(Object self);
	
	public abstract List<E> toList(Object self);

	@SuppressWarnings("unchecked")
	public void setAssociationAsOwner(KAssociation<?, E> associationAsOwner) {
		this.associationAsOwner = (KAssociation<? super Object, E>) associationAsOwner;
	}

	@SuppressWarnings("unchecked")
	public void setAssociationAsTarget(KAssociation<E, ?> associationAsTarget) {
		this.associationAsTarget = (KAssociation<E, ? super Object>) associationAsTarget;
	}

}
