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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.umlatj.internal.kernel.KProperty;
import org.umlatj.internal.util.proxy.FieldProxy;

/**
 * 
 * @author Laurent Legrand
 * 
 * @param <O>
 * @param <E>
 */
public class CollectionProperty<E> extends KProperty<E> {

	class CollectionProxy implements Collection<E> {

		Object self;

		Collection<E> proxy;

		CollectionProxy(Object self, Collection<E> proxy) {
			this.self = self;
			this.proxy = proxy;
		}

		public boolean add(E e) {
			return CollectionProperty.this.add(self, e);
		}

		public boolean addAll(Collection<? extends E> c) {
			// TODO
			return proxy.addAll(c);
		}

		public void clear() {
			proxy.clear();
		}

		public boolean contains(Object o) {
			return proxy.contains(o);
		}

		public boolean containsAll(Collection<?> c) {
			return proxy.containsAll(c);
		}

		public boolean equals(Object o) {
			return proxy.equals(o);
		}

		public int hashCode() {
			return proxy.hashCode();
		}

		public boolean isEmpty() {
			return proxy.isEmpty();
		}

		public Iterator<E> iterator() {
			return proxy.iterator();
		}

		@SuppressWarnings("unchecked")
		public boolean remove(Object o) {
			return CollectionProperty.this.remove(this.self, (E) o);
		}

		public boolean removeAll(Collection<?> c) {
			// TODO
			return proxy.removeAll(c);
		}

		public boolean retainAll(Collection<?> c) {
			// TODO
			return proxy.retainAll(c);
		}

		public int size() {
			return proxy.size();
		}

		public Object[] toArray() {
			return proxy.toArray();
		}

		public <T> T[] toArray(T[] a) {
			return proxy.toArray(a);
		}

	}

	FieldProxy<Collection<E>> field;

	public CollectionProperty(FieldProxy<Collection<E>> field) {
		this(field.getName(), 0, -1);
		this.field = field;
	}

	public CollectionProperty(String name, int lower, int upper) {
		super(name, lower, upper);
	}

	@Override
	public boolean proceedAdd(Object self, E element) {
		return this.get(self).add(element);
	}

	@Override
	public boolean contains(Object self, E element) {
		return this.get(self).contains(element);
	}

	@Override
	public boolean proceedRemove(Object self, E element) {
		return this.get(self).remove(element);
	}

	@Override
	public E evict(Object self) {
		return null;
	}

	protected Collection<E> get(Object self) {
		return this.field.get(self);
	}

	@Override
	public List<E> toList(Object self) {
		return new ArrayList<E>(this.get(self));
	}

	public Collection<E> newCollectionProxy(Object self,
			Collection<E> collection) {
		return new CollectionProxy(self, collection);
	}

	@Override
	protected Object convert(List<E> list) {
		return list;
	}

}
