package org.umlatj.internal.kernel;

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.kernel.contraint.ConstraintBuilder;
import org.umlatj.internal.util.proxy.TypeProxy;

public class KClassifier extends KType {

	Map<String, KProperty<?>> attributes = new HashMap<String, KProperty<?>>();

	Map<String, KConstraint> constraints = new HashMap<String, KConstraint>();

	ConstraintBuilder constraintBuilder = null;
	
//	new ConstraintBuilder() {
//
//		@Override
//		protected KConstraint fromLiteral(Literal literal) {
//			/*
//			 * String value = literal.getValue(); value = "is" +
//			 * Character.toUpperCase(value.charAt(0)) + value.substring(1);
//			 * return KClassifier.this.getConstraint(value);
//			 */
//			return new TrueConstraint();
//		}
//
//	};

	public KClassifier(TypeProxy type) {
		super(type);
	}

	public void addConstraint(KConstraint constraint) {
		this.constraints.put(constraint.getName(), constraint);
	}

	public KConstraint getConstraint(String name) {
		return this.constraints.get(name);
	}

	public void addAttribute(KProperty<?> property) {
		this.attributes.put(property.getName(), property);
	}

	public KProperty<?> getAttribute(String name) {
		return this.attributes.get(name);
	}

	public ConstraintBuilder getConstraintBuilder() {
		return constraintBuilder;
	}

}
