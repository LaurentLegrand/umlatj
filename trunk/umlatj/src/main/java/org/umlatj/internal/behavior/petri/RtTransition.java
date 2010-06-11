package org.umlatj.internal.behavior.petri;

import java.util.HashSet;
import java.util.Set;

import org.umlatj.internal.behavior.petri.RtNet.NetInstance;
import org.umlatj.internal.kernel.KNamedElement;


/**
 * 
 * 
 * @author Laurent Legrand
 * @version $Id$
 */
public class RtTransition extends KNamedElement {

	/**
	 * The Perti Net that owns this transition.
	 */
	private RtNet net;

	/**
	 * The set of input arcs.
	 */
	private Set<RtInputArc> inputArcs = new HashSet<RtInputArc>();

	/**
	 * The set of output arcs.
	 */
	private Set<RtOutputArc> outputArcs = new HashSet<RtOutputArc>();

	/**
	 * The set of places that will be reset when this transition fires.
	 */
	private Set<RtPlace> resets = new HashSet<RtPlace>();

	public RtTransition(String name) {
		super(name);
	}

	/**
	 * Verify is this transition is enabled.
	 * 
	 * @param instance
	 *            the instance containing the marking.
	 * @return <code>true</code> if this transition is enabled.
	 */
	public boolean isEnabled(NetInstance instance) {
		if (!this.net.isEnabled(this, instance)) {
			return false;
		}
		// verify that input places contains enough tokens
		for (RtInputArc arc : this.inputArcs) {
			if (!arc.canConsume(instance)) {
				return false;
			}
		}
		// verify that output places can contain tokens that will be produced
		for (RtOutputArc arc : this.outputArcs) {
			if (!arc.canProduce(instance)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Run the pre-fire sequence: consuming tokens from input places.
	 * 
	 * Note. the transition enablement is not verified.
	 * 
	 * @param instance
	 *            the instance containing the marking.
	 * @throws IllegalStateException
	 */
	protected void preFire(NetInstance instance) throws IllegalStateException {
		this.net.onPreFire(this, instance);
		for (RtInputArc arc : this.inputArcs) {
			arc.consume(instance);
		}
	}

	/**
	 * Run the post-fire sequence: producing tokens into output places.
	 * 
	 * @param instance
	 *            the instance containing the marking.
	 */
	protected void postFire(NetInstance instance) {
		for (RtOutputArc arc : this.outputArcs) {
			arc.produce(instance);
		}
		for (RtPlace place : this.resets) {
			place.reset(instance);
		}
		this.net.onPostFire(this, instance);
	}

	/**
	 * Fire the transition: verify the enablement of the transition, pre-fire
	 * and post-fire.
	 * 
	 * @param instance
	 *            the instance containing the marking.
	 * 
	 * @throws IllegalStateException
	 */
	public void fire(NetInstance instance) {
		if (!this.isEnabled(instance)) {
			// TODO message
			throw new IllegalStateException("Pre conditions not met");
		}
		this.preFire(instance);
		this.postFire(instance);
	}

	public RtNet getNet() {
		return net;
	}

	public void setNet(RtNet net) {
		this.net = net;
	}

	public void addInputArc(RtInputArc inputArc) {
		this.inputArcs.add(inputArc);
	}

	public void addOutputArc(RtOutputArc outputArc) {
		this.outputArcs.add(outputArc);
	}

	public void addReset(RtPlace place) {
		this.resets.add(place);
	}

}
