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
