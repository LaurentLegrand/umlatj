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
