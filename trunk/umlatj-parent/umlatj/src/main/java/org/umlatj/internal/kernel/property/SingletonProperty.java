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
