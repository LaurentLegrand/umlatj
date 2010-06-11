package org.umlatj.internal.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classes {

	public static List<Field> findFields(Class<?> type, Class<? extends Annotation> annotation) {
		List<Field> list = new ArrayList<Field>();
		while (type != null) {
			for (Field field : type.getDeclaredFields()) {
				if (field.getAnnotation(annotation) != null) {
					list.add(field);
				}
			}
			type = type.getSuperclass();
		}
		return list;
	}

	public static List<Method> findMethods(Class<?> type, Class<? extends Annotation> annotation) {
		List<Method> list = new ArrayList<Method>();
		while (type != null) {
			for (Method method : type.getDeclaredMethods()) {
				if (method.getAnnotation(annotation) != null) {
					list.add(method);
				}
			}
			type = type.getSuperclass();
		}
		return list;
	}

	/**
	 * From http://www.artima.com/weblogs/viewpost.jsp?thread=208860
	 * 
	 * Get the underlying class for a type, or null if the type is a variable
	 * type.
	 * 
	 * @param type
	 *            the type
	 * @return the underlying class
	 */
	public static Class<?> getClass(Type type) {
		if (type instanceof Class) {
			return (Class<?>) type;
		} else if (type instanceof ParameterizedType) {
			return getClass(((ParameterizedType) type).getRawType());
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type).getGenericComponentType();
			Class<?> componentClass = getClass(componentType);
			if (componentClass != null) {
				return Array.newInstance(componentClass, 0).getClass();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Inspired from http://www.artima.com/weblogs/viewpost.jsp?thread=208860
	 * Generalized to manage genericInterfaces.
	 * 
	 * Get the actual type arguments a child class has used to extend a generic
	 * base class.
	 * 
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @return a list of the raw classes for the actual type arguments.
	 */
	public static <T> List<Class<?>> getTypeArguments(Class<T> baseClass,
	        Class<? extends T> childClass) {
		Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		fillResolvedTypes(resolvedTypes, childClass);
		// System.out.println(resolvedTypes);
		List<Class<?>> list = new ArrayList<Class<?>>();
		for (TypeVariable<?> variable : baseClass.getTypeParameters()) {
			Type baseType = variable;
			while (resolvedTypes.containsKey(baseType)) {
				baseType = resolvedTypes.get(baseType);
			}
			// System.out.println("found:" + baseType);
			list.add(getClass(baseType));
		}
		return list;
	}

	static void fillResolvedTypes(Map<Type, Type> resolvedTypes, Type type) {
		if (type == null) {
			return;
		}
		if (type instanceof Class<?>) {
			Class<?> cl = (Class<?>) type;
			fillResolvedTypes(resolvedTypes, cl.getGenericSuperclass());
			for (Type t : cl.getGenericInterfaces()) {
				fillResolvedTypes(resolvedTypes, t);
			}
		} else if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Class<?> rawType = (Class<?>) parameterizedType.getRawType();

			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			java.lang.reflect.TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
			for (int i = 0; i < actualTypeArguments.length; i++) {
				resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
			}
			fillResolvedTypes(resolvedTypes, rawType);
		}
	}

}
