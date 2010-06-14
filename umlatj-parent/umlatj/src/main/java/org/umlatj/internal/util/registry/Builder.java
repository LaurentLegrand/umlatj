package org.umlatj.internal.util.registry;

import java.lang.annotation.Annotation;

import org.umlatj.internal.kernel.KType;


public abstract class Builder<E extends KType> {

	final Class<? extends Annotation> annotationType;

	public Builder(Class<? extends Annotation> annotationType) {
		this.annotationType = annotationType;
	}

	public boolean accept(Class<?> type) {
		return type.getAnnotation(this.annotationType) != null;
	}

	public abstract E build(Class<?> type);

}
