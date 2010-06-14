package org.umlatj.internal.action;

import org.umlatj.internal.behavior.KBehavior;
import org.umlatj.internal.behavior.KAction;
import org.umlatj.internal.behavior.KBehavior.BehaviorInstance;
import org.umlatj.internal.util.proxy.MethodProxy;

public class CallBehaviorAction extends KAction {

	MethodProxy<?> callback;

	public CallBehaviorAction(MethodProxy<?> method) {
		super(method);
	}

	@Override
	public void afterReturning(Object self, Object returning, Object... args) {
		if (returning == null) {
			super.afterReturning(self, returning, args);
			return;
		}
		BehaviorInstance instance = KBehavior.adapt(returning);
		if (instance != null) {
			instance.setOnCompletion(this.newCallback(self, returning));
		}

	}

	public Runnable newCallback(final Object self, final Object returning, final Object... args) {
		return new Runnable() {
			//@Override
			public void run() {
				// execute callback
				CallBehaviorAction.this.callCallback(self, returning);
				// verify post conditions
				CallBehaviorAction.super.afterReturning(self, returning, args);
			}
		};
	}
	
	void callCallback(Object self, Object returning) {
		if (this.callback != null) {
			this.callback.invoke(self, returning);
		}
	}

	public MethodProxy<?> getCallback() {
		return callback;
	}

	public void setCallback(MethodProxy<?> callback) {
		this.callback = callback;
	}
}
