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

package org.umlatj.internal.behavior;

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.kernel.KType;
import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.internal.util.registry.Registry;

import org.umlatj.petri.Net;
import org.umlatj.petri.Net.Marking;


public class KBehavior extends KType {

	public class BehaviorInstance {

		public final KBehavior type = KBehavior.this;

		public final Object self;

		public final Marking marking;

		Runnable onCompletion;

		public BehaviorInstance(Object self) {
			this.self = self;
			this.marking = KBehavior.this.net.newMarking(this);
		}

		/**
		 * @return the onCompletion
		 */
		public Runnable getOnCompletion() {
			return onCompletion;
		}

		/**
		 * @param onCompletion
		 *            the onCompletion to set
		 */
		public void setOnCompletion(Runnable onCompletion) {
			this.onCompletion = onCompletion;
		}

	}

	Net net = new Net();

	KConstraint preConditions;

	KConstraint postConditions;

	public KBehavior(TypeProxy type) {
		super(type);
	}

	Map<String, KAction> actions = new HashMap<String, KAction>();

	protected void onCompletion(KAction action, BehaviorInstance instance) {
		if (this.net.isDead(instance.marking)) {
			this.onCompletion(instance);
		}
	}

	protected void onCompletion(BehaviorInstance instance) {
		if (instance.getOnCompletion() != null) {
			instance.getOnCompletion().run();
		}
	}

	public BehaviorInstance newInstance(Object self) {
		return new BehaviorInstance(self);
	}

	public void addAction(KAction action) {
		this.actions.put(action.getName(), action);
		action.setBehavior(this);
	}

	public KAction getAction(String name) {
		return this.actions.get(name);
	}

	static Registry<KBehavior> registry = new Registry<KBehavior>();

	public static KBehavior get(Class<?> type) {
		return registry.get(type);
	}

	public static BehaviorInstance adapt(Object self) {
		return null;
	}

	/**
	 * @return the net
	 */
	public Net getNet() {
		return net;
	}

}
