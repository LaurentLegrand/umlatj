/**
 * 
 */
package org.umlatj.internal.activity;

import org.umlatj.activity.Activity;
import org.umlatj.internal.behavior.BehaviorBuilder;
import org.umlatj.internal.util.proxy.TypeProxyImpl;


public class ActivityBuilder extends BehaviorBuilder<DeclaredActivity> {

	public ActivityBuilder() {
		super(Activity.class);
	}

	public DeclaredActivity newBehavior(Class<?> type) {
		return new DeclaredActivity(new TypeProxyImpl(type));
	}

}