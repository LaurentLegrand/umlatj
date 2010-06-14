package org.umlatj.internal.reflect;

public abstract class Builder<E> {

	private int priority = Integer.MIN_VALUE;

	public Builder() {
	}

	public abstract boolean accept(Class<?> type);

	public abstract E newInstance(Class<?> type);

	public abstract void build(Class<?> type, E instance);

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
