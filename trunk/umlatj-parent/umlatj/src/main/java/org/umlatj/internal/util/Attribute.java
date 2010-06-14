package org.umlatj.internal.util;

public class Attribute<E> {

	E e;

	public Attribute() {

	}

	public Attribute(E e) {
		this.e = e;
	}

	public E get() {
		return this.e;
	}

	public void set(E e) {
		this.e = e;
	}

}
