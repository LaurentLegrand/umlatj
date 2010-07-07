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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.umlatj.internal.kernel.KProperty;
import org.umlatj.internal.util.proxy.FieldProxy;

public class MapProperty<K, E> extends KProperty<E> {

	FieldProxy<Map<K, E>> field;

	org.umlatj.internal.util.proxy.FieldProxy<K> key;

	public MapProperty(FieldProxy<Map<K, E>> field, FieldProxy<K> key) {
		super(field.getName(), 0, -1);
		this.field = field;
		this.key = key;
	}

	@Override
	public boolean contains(Object self, E element) {
		return this.field.get(self).containsKey(this.key.get(element));
	}

	@Override
	public E evict(Object self) {
		return null;
	}

	@Override
	protected boolean proceedAdd(Object self, E element) {
		this.field.get(self).put(this.key.get(element), element);
		return true;
	}

	@Override
	protected boolean proceedRemove(Object self, E element) {
		this.field.get(self).remove(this.key.get(element));
		return true;
	}

	@Override
	public List<E> toList(Object self) {
		return new ArrayList<E>(this.field.get(self).values());
	}

	@Override
	protected Object convert(List<E> list) {
		Map<K, E> map = new HashMap<K, E>();
		for (E e : list) {
			map.put(this.key.get(e), e);
		}
		return map;
	}

}
