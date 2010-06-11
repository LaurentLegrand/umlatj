package org.umlatj.internal.action;

import org.umlatj.internal.behavior.KAction;
import org.umlatj.internal.util.proxy.MethodProxy;

public class CallOperationAction extends KAction {

	public CallOperationAction(MethodProxy<?> method) {
		super(method);
	}

}
