package org.umlatj.internal.behavior;

import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.petri.InputArc;
import org.umlatj.petri.Place;


public class KOpaqueBehavior extends KBehavior {

	KConstraint preConditions;

	KConstraint postConditions;

	Place place;

	public KOpaqueBehavior(TypeProxy type) {
		super(type);
		this.place = new Place(this.net, (short) 1);
	}

	@Override
	public void addAction(KAction action) {
		super.addAction(action);
		new InputArc(this.place, action.getPreTransition());
	}

	protected void onComplete(KAction action, Object self) {
		BehaviorInstance instance = KBehavior.adapt(self);
		if (instance != null && instance.getOnCompletion() != null) {
			instance.getOnCompletion().run();
		}
	}

	public KConstraint getPreConditions() {
		return preConditions;
	}

	public void setPreConditions(KConstraint preConditions) {
		this.preConditions = preConditions;
	}

	public KConstraint getPostConditions() {
		return postConditions;
	}

	public void setPostConditions(KConstraint postConditions) {
		this.postConditions = postConditions;
	}

}
