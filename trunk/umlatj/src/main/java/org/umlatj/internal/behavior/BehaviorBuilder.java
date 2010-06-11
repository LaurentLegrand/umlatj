/**
 * 
 */
package org.umlatj.internal.behavior;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.umlatj.action.Action;
import org.umlatj.activity.Activity;
import org.umlatj.behavior.OpaqueBehavior;
import org.umlatj.internal.action.CallBehaviorAction;
import org.umlatj.internal.action.CallOperationAction;
import org.umlatj.internal.kernel.KClassifier;
import org.umlatj.internal.reflect.Types;
import org.umlatj.internal.util.Classes;
import org.umlatj.internal.util.proxy.MethodProxyImpl;
import org.umlatj.internal.util.registry.Builder;



public abstract class BehaviorBuilder<E extends KBehavior> extends Builder<E> {

	public BehaviorBuilder(Class<? extends Annotation> annotationType) {
		super(annotationType);
	}

	public E build(Class<?> type) {
		E behavior = this.newBehavior(type);
		for (Method method : Classes.findMethods(type, Action.class)) {
			KAction rtAction = newAction(method);
			behavior.addAction(rtAction);
		}

		return behavior;
	}

	protected abstract E newBehavior(Class<?> type);

	KAction newAction(Method method) {
		KClassifier classifier = Types.asClassifier(method.getDeclaringClass());
		Class<?> returnType = method.getReturnType();
		KAction rtAction = (returnType.getAnnotation(Activity.class) != null || returnType
		        .getAnnotation(OpaqueBehavior.class) != null) ? newCallBehaviorAction(method)
		        : new CallOperationAction(new MethodProxyImpl<Void>(method));
		Action action = method.getAnnotation(Action.class);
		if (action.preConditions().length() != 0) {
			rtAction.setPreCondition(classifier.getConstraintBuilder()
			        .build(action.preConditions()));
		}
		if (action.postConditions().length() != 0) {
			rtAction.setPostCondition(classifier.getConstraintBuilder().build(
			        action.postConditions()));
		}
		return rtAction;
	}

	KAction newCallBehaviorAction(Method method) {
		CallBehaviorAction callBehaviorAction = new CallBehaviorAction(
		        new MethodProxyImpl<Void>(method));
		try {
			callBehaviorAction.setCallback(new MethodProxyImpl<Void>(method.getDeclaringClass()
			        .getDeclaredMethod(method.getName(), method.getReturnType())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return callBehaviorAction;
	}
}