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

package org.umlatj.internal.reflect;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import org.umlatj.internal.util.proxy.FieldProxy;
import org.umlatj.internal.util.proxy.FieldProxyImpl;
import org.umlatj.internal.util.proxy.MethodProxy;
import org.umlatj.internal.util.proxy.MethodProxyImpl;
import org.umlatj.internal.util.proxy.PropertyBasedImpl;
import org.umlatj.internal.util.proxy.TypeProxyImpl;
import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Constraint;
import org.umlatj.kernel.Property;

public class ClassifierBuilder extends Builder<KClassifier> {

	class Match {

		/**
		 * The field proxy
		 */
		FieldProxy<?> proxy;

		/**
		 * The property value
		 */
		Property property;

		/**
		 * The type of the property
		 */
		Class<?> type;

		/**
		 * The generic type
		 */
		Type genericType;

		public FieldProxy<?> getProxy() {
			return proxy;
		}

		public Property getProperty() {
			return property;
		}

		public Class<?> getType() {
			return type;
		}

		public Type getGenericType() {
			return genericType;
		}

	}

	@Override
	public KClassifier newInstance(Class<?> type) {
		return new KClassifier(new TypeProxyImpl(type));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void build(Class<?> type, KClassifier instance) {

		// add super class if any
		if (type.getSuperclass().getAnnotation(Classifier.class) != null) {
			instance.addGeneral(Types.asClassifier(type.getSuperclass()));
		}

		for (Match match : this.findMatches(type)) {
			KProperty property = null;
			if (Collection.class.isAssignableFrom(match.type)) {
				property = new CollectionProperty(match.proxy);
			} else if (Map.class.isAssignableFrom(match.type)) {
				// qualified
				try {
					// class used as value
					Class<?> valueType = (Class<?>) ((ParameterizedType) match
							.getGenericType()).getActualTypeArguments()[1];
					valueType.getDeclaredFields();

					/*
					 * the qualified value must correspond to a declared field
					 * of the value type
					 */
					Field key = valueType.getDeclaredField(match.getProperty()
							.qualifier());
					property = new MapProperty(match.getProxy(),
							new FieldProxyImpl(key));
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			} else {
				property = new SingletonProperty(match.getProxy());
			}
			if (property != null) {
				instance.addOwnedAttribute(property);
				// manage derived union
				if (!"".equals(match.property.subset())) {
					KProperty union = instance.getAttribute(match.property
							.subset());
					if (union != null) {
						union.addSubset(property);
					} else {
						// TODO raise warning!
					}
				}
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

	List<Match> findMatches(Class<?> type) {
		List<Match> list = new ArrayList<Match>();
		for (Field field : Classes.findFields(type, Property.class)) {
			Match match = new Match();
			match.proxy = new FieldProxyImpl(field);
			match.property = field.getAnnotation(Property.class);
			match.type = field.getType();
			match.genericType = field.getGenericType();
			list.add(match);
		}

		try {
			for (PropertyDescriptor descriptor : Introspector.getBeanInfo(type,
					type.getSuperclass()).getPropertyDescriptors()) {
				Property property = descriptor.getReadMethod().getAnnotation(
						Property.class);
				if (property == null) {
					continue;
				}

				Match match = new Match();
				match.proxy = new PropertyBasedImpl(descriptor);
				match.property = property;
				match.type = descriptor.getReadMethod().getReturnType();
				match.genericType = descriptor.getReadMethod()
						.getGenericReturnType();
				list.add(match);
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean accept(Class<?> type) {
		return type.getAnnotation(Classifier.class) != null;
	}

}
