package org.umlatj.internal.profile.behavior.basic;

import org.umlatj.internal.behavior.KAction;
import org.umlatj.petri.OutputArc;
import org.umlatj.petri.Place;
import org.umlatj.petri.Transition;


public class KGoto extends OutputArc {

	public KGoto(Transition transition, Place place) {
		super(transition, place);
	}

	KAction from;

	KAction to;

	KBasic basic;

	/**
	 * @return the basic
	 */
	public KBasic getBasic() {
		return basic;
	}

	/**
	 * @param basic
	 *            the basic to set
	 */
	public void setBasic(KBasic basic) {
		this.basic = basic;
		//this.from.getPostTransition(), this, this.basic.getNet().getPlace(   this.to.getName()));
	}

	/**
	 * @return the from
	 */
	public KAction getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(KAction from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public KAction getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(KAction to) {
		this.to = to;
	}

}
