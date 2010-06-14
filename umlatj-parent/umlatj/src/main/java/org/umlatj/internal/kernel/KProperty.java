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
