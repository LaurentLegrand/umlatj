package org.umlatj.internal.kernel.property;

import java.util.Collections;
import java.util.List;

import org.umlatj.internal.kernel.KProperty;
import org.umlatj.internal.util.proxy.FieldProxy;

/**
 * Singleton in the mathematical sense: a set with only one element (cf. <a
 * href="http://en.wikipedia.org/wiki/Singleton_%28mathematics%29">Singleton
 * (mathematics)</a>.
 * 
 * The multiplicity of a {@link SingletonProperty} is 0, 1.
 * 
 * @author Laurent Legrand
 * 
 * @param <O>
 * @param <E>
 */
public class SingletonProperty<E> extends KProperty<E> {

	FieldProxy<E> field;

	/**
	 * 
	 */
	public SingletonProperty(FieldProxy<E> field) {
		super(field.getName(), 0, 1);
		this.field = field;
	}

	@Override
	public boolean proceedAdd(Object owner, E element) {
		this.set(owner, element);
		return true;
	}

	@Override
	public boolean contains(Object owner, E element) {
		return this.get(owner) == element;
	}

	@Override
	public boolean proceedRemove(Object owner, E element) {
		this.set(owner, null);
		return true;
	}

	/**
	 * Return always the value of this property.
	 */
	@Override
	public E evict(Object owner) {
		return this.get(owner);
	}

	/**
	 * 
	 * 
	 * @param self
	 * @param element
	 */
	public void set(Object owner, E element) {
		this.field.set(owner, element);
	}

	/**
	 * 
	 * @param self
	 * @return
	 */
	public E get(Object owner) {
		return this.field.get(owner);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<E> toList(Object self) {
		E e = this.get(self);
		return (List<E>) ((e == null) ? Collections.emptyList() : Collections.singletonList(e));
	}

}
