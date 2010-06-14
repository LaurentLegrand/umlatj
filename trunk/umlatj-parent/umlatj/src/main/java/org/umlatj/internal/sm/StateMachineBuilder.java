/**
 * 
 */
package org.umlatj.internal.sm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.umlatj.internal.util.Classes;
import org.umlatj.internal.util.proxy.FieldProxyImpl;
import org.umlatj.internal.util.proxy.MethodProxyImpl;
import org.umlatj.internal.util.proxy.TypeProxyImpl;
import org.umlatj.internal.util.registry.Builder;
import org.umlatj.sm.State;
import org.umlatj.sm.StateMachine;
import org.umlatj.sm.Transition;
import org.umlatj.sm.State.Entry;
import org.umlatj.sm.State.Exit;



public class StateMachineBuilder extends Builder<DeclaredStateMachine> {

	public StateMachineBuilder() {
		super(StateMachine.class);
	}

	public DeclaredStateMachine build(Class<?> type) {
		DeclaredStateMachine machine = new DeclaredStateMachine(new TypeProxyImpl(type));
		List<Field> list = Classes.findFields(type, State.class);
		if (list.isEmpty()) {
			throw new IllegalArgumentException("Unable to find a field annotated with "
			        + State.class);
		}
		Field currentState = list.get(0);
		currentState.setAccessible(true);
		if (!currentState.getType().isEnum()) {
			throw new IllegalArgumentException(String.format(
			        "The field %s is not bound to an enum", currentState));
		}
		machine.setCurrentStateField(new FieldProxyImpl<Object>(currentState));

		for (Object constant : currentState.getType().getEnumConstants()) {
			DeclaredState state = new DeclaredState(constant);
			machine.addState(state);
		}

		for (Method entry : Classes.findMethods(type, Entry.class)) {
			DeclaredState state = machine.getState(entry.getAnnotation(Entry.class).value());
			if (state == null) {
				/*
				 * logger.warning(String.format(
				 * "The state %s declared in %s has not been found: skip", entry
				 * .getAnnotation(Entry.class).value(), entry));
				 */
			} else {
				state.setEntry(new MethodProxyImpl<Void>(entry));
			}
		}

		for (Method exit : Classes.findMethods(type, Exit.class)) {
			DeclaredState state = machine.getState(exit.getAnnotation(Exit.class).value());
			if (state == null) {
				/*
				 * logger.warning(String.format(
				 * "The state %s declared in %s has not been found: skip",
				 * exit.getAnnotation( Entry.class).value(), exit));
				 */
			} else {
				state.setExit(new MethodProxyImpl<Void>(exit));
			}
		}

		for (Method method : Classes.findMethods(type, Transition.class)) {
			Transition transition = method.getAnnotation(Transition.class);
			DeclaredState source = machine.getState(transition.source());
			if (source == null) {
				throw new IllegalArgumentException("Unable to find state named "
				        + transition.source());
			}
			DeclaredState target = machine.getState(transition.target());
			if (target == null) {
				throw new IllegalArgumentException("Unable to find state named "
				        + transition.target());
			}
			DeclaredTransition reflectTransition = new DeclaredTransition(method.getName());
			reflectTransition.setSource(source);
			reflectTransition.setTarget(target);

			machine.addTransition(reflectTransition);
		}

		return machine;
	}

}