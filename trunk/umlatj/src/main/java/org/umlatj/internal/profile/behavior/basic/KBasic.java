package org.umlatj.internal.profile.behavior.basic;

import org.umlatj.internal.behavior.KAction;
import org.umlatj.internal.behavior.KBehavior;
import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.petri.InputArc;
import org.umlatj.petri.Place;


public class KBasic extends KBehavior {

	public KBasic(TypeProxy type) {
		super(type);
	}

	/**
	 * Initialize the input place of the first action.
	 */
	public void setFirstAction(String name) {
//		this.getNet().getPlace(name).setInitial((short) 1);
	}

	public void addGoto(KGoto declaredGoto) {
		declaredGoto.setBasic(this);
	}

	@Override
	public void addAction(KAction action) {
		super.addAction(action);
//		Place place = new Place(action.getName());
//		Petri.link(this.getNet(), place);
//		Petri.link(place, new InputArc(), action.getPreTransition());
	}

}
