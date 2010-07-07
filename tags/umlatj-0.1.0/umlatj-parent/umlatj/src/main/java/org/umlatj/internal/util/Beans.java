package org.umlatj.internal.util;

import java.beans.Introspector;

public class Beans {

	public static String getPropertyName(String name) {
		if (name.startsWith("is")) {
			return Introspector.decapitalize(name.substring(2));
		}
		if (name.startsWith("get")) {
			return Introspector.decapitalize(name.substring(3));
		}
		return name;
	}

}
