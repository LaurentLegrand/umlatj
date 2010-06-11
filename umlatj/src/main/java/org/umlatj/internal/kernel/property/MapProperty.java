package org.umlatj.internal.kernel.property;

import java.util.ArrayList;
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
}
