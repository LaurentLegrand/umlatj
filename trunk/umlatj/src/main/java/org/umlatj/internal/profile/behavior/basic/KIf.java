package org.umlatj.internal.profile.behavior.basic;

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.behavior.KAction;
import org.umlatj.internal.behavior.KBehavior.BehaviorInstance;
import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.petri.OutputArc;
import org.umlatj.petri.Place;
import org.umlatj.petri.Transition;
import org.umlatj.petri.Net.Marking;

public class KIf extends OutputArc {

	public KIf(Transition transition, Place place) {
		super(transition, place);
		// TODO Auto-generated constructor stub
	}

	KConstraint condition;

	KAction from;

	KAction then;

	KAction otherwise;

	KBasic basic;

	Map<KAction, Place> places = new HashMap<KAction, Place>();

	/**
	 * @return the basic
	 */
	public KBasic getBasic() {
		return basic;
	}

	@Override
	public boolean canProduce(Marking marking) {
		return true;
	}

	@Override
	public void produce(Marking marking) {

		if (this.condition.verify(((BehaviorInstance) marking.context).self)) {
			this.places.get(this.then).produce(marking);
		} else {
			this.places.get(this.otherwise).produce(marking);
		}
	}

	/**
	 * @param basic
	 *            the basic to set
	 */
	public void setBasic(KBasic basic) {
		this.basic = basic;
//		Petri.link(this.from.getPostTransition(), this, this.basic.getNet().getPlace(this.then.getName()));
//		Petri.link(this.from.getPostTransition(), this, this.basic.getNet().getPlace(this.otherwise.getName()));
//		//this.from.getPostTransition().addOutputArc(this);
//		this.places.put(this.then, this.basic.getNet().getPlace(this.then.getName()));
//		this.places.put(this.otherwise, this.basic.getNet().getPlace(this.otherwise.getName()));
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
	 * @return the then
	 */
	public KAction getThen() {
		return then;
	}

	/**
	 * @param then
	 *            the then to set
	 */
	public void setThen(KAction then) {
		this.then = then;
	}

	/**
	 * @return the otherwise
	 */
	public KAction getOtherwise() {
		return otherwise;
	}

	/**
	 * @param otherwise
	 *            the otherwise to set
	 */
	public void setOtherwise(KAction otherwise) {
		this.otherwise = otherwise;
	}

	/**
	 * @return the condition
	 */
	public KConstraint getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(KConstraint condition) {
		this.condition = condition;
	}

}
