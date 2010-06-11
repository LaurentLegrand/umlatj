package org.umlatj.internal.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

import org.umlatj.internal.kernel.KClassifier;
import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.kernel.KProperty;
import org.umlatj.internal.kernel.contraint.TargetConstraint;
import org.umlatj.internal.kernel.contraint.ThisConstraint;
import org.umlatj.internal.kernel.property.CollectionProperty;
import org.umlatj.internal.kernel.property.MapProperty;
import org.umlatj.internal.kernel.property.SingletonProperty;
import org.umlatj.internal.util.Classes;
import org.umlatj.internal.util.proxy.FieldProxyImpl;
import org.umlatj.internal.util.proxy.MethodProxy;
import org.umlatj.internal.util.proxy.MethodProxyImpl;
import org.umlatj.internal.util.proxy.TypeProxyImpl;
import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Constraint;
import org.umlatj.kernel.Property;

public class ClassifierBuilder extends Builder<KClassifier> {

	@Override
	public KClassifier newInstance(Class<?> type) {
		return new KClassifier(new TypeProxyImpl(type));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void build(Class<?> type, KClassifier instance) {

		for (Field field : Classes.findFields(type, Property.class)) {
			KProperty property = null;
			if (Collection.class.isAssignableFrom(field.getType())) {
				property = new CollectionProperty( new FieldProxyImpl(
						field));
			} else if (Map.class.isAssignableFrom(field.getType())) {
				// qualified
				try {
					// class used as value
					Class<?> valueType = (Class<?>) ((ParameterizedType) field
							.getGenericType()).getActualTypeArguments()[1];
					valueType.getDeclaredFields();

					/*
					 * the qualified value must correspond to a declared field
					 * of the value type
					 */
					Field key = valueType.getDeclaredField(field.getAnnotation(
							Property.class).qualifier());
					property = new MapProperty(new FieldProxyImpl(
							field), new FieldProxyImpl(key));
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			} else {
				property = new SingletonProperty(new FieldProxyImpl(
						field));
			}
			if (property != null) {
				instance.addAttribute(property);
			}
		}

		for (Method method : Classes.findMethods(type, Constraint.class)) {
			// TODO verify type is boolean, etc.
			MethodProxy<Boolean> proxy = new MethodProxyImpl<Boolean>(method);
			KConstraint constraint = (Modifier.isStatic(method.getModifiers())) ? new TargetConstraint(
					proxy)
					: new ThisConstraint(proxy);
			instance.addConstraint(constraint);
		}
	}

	@Override
	public boolean accept(Class<?> type) {
		return type.getAnnotation(Classifier.class) != null;
	}

}
