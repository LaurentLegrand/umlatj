/**
 * 
 */
package org.umlatj.internal.behavior;

import org.umlatj.behavior.OpaqueBehavior;
import org.umlatj.internal.kernel.KClassifier;
import org.umlatj.internal.reflect.Types;


public class OpaqueBehaviorBuilder extends BehaviorBuilder<KOpaqueBehavior> {

	public OpaqueBehaviorBuilder() {
		super(OpaqueBehavior.class);
	}

	public KOpaqueBehavior newBehavior(Class<?> type) {
		KClassifier classifier = Types.asClassifier(type);

		KOpaqueBehavior behavior = new KOpaqueBehavior(classifier.getType());
		OpaqueBehavior opaqueBehavior = type.getAnnotation(OpaqueBehavior.class);
		if (opaqueBehavior.preConditions().length() != 0) {
			behavior.setPreConditions(classifier.getConstraintBuilder().build(
			        opaqueBehavior.preConditions()));
		}
		if (opaqueBehavior.postConditions().length() != 0) {
			behavior.setPostConditions(classifier.getConstraintBuilder().build(
			        opaqueBehavior.postConditions()));
		}
		return behavior;
	}

}