package org.umlatj.internal.behavior.petri;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.kernel.KType;
import org.umlatj.internal.util.proxy.TypeProxy;


public class RtNet extends KType {

	public class NetInstance {

		public final RtNet net = RtNet.this;

		short[] marking;

		public NetInstance() {
			RtNet.this.reset(this);
		}

		/**
		 * @return the net
		 */
		public RtNet getNet() {
			return net;
		}

	}

	Map<String, RtTransition> transitions = new HashMap<String, RtTransition>();

	Map<String, RtPlace> places = new HashMap<String, RtPlace>();

	RtPlace liveness = new RtPlace("-liveness-");

	RtOutputArc incLiveness = new RtOutputArc(this.liveness);

	RtInputArc decLiveness = new RtInputArc(this.liveness);

	public RtNet(TypeProxy type) {
		super(type);
		this.addPlace(this.liveness);
	}

	public RtNet() {
		this(new TypeProxy() {

			//@Override
			public String getName() {
				return "RtNet";
			}

			//@Override
			public Class<?> getType() {
				return RtNet.class;
			}
		});
	}

	public NetInstance newInstance() {
		return new NetInstance();
	}

	protected void reset(NetInstance instance) {
		instance.marking = new short[this.places.size()];
		for (RtPlace place : this.places.values()) {
			place.init(instance);
		}
	}

	protected boolean isEnabled(RtTransition transition, NetInstance instance) {
		return this.incLiveness.canProduce(instance);
	}

	protected void onPreFire(RtTransition transition, NetInstance instance) {
		this.incLiveness.produce(instance);
	}

	protected void onPostFire(RtTransition transition, NetInstance instance) {
		this.decLiveness.consume(instance);
	}

	/**
	 * Verify if the firing sequence is empty.
	 * 
	 * @return
	 */
	public boolean isDead(NetInstance instance) {
		if (this.liveness.contains(instance)) {
			return false;
		}
		for (RtTransition transition : this.transitions.values()) {
			if (transition.isEnabled(instance)) {
				return false;
			}
		}
		return true;
	}

	public void addTransition(RtTransition transition) {

		transition.setNet(this);
		this.transitions.put(transition.getName(), transition);
	}

	public RtTransition getTransition(String name) {
		return this.transitions.get(name);
	}
	
	public RtPlace getPlace(String name) {
		return this.places.get(name);
	}

	public void addPlace(RtPlace place) {
		place.setIndex(this.places.size());
		this.places.put(place.getName(), place);
	}

	/**
	 * @return the transitions
	 */
	public Collection<RtTransition> getTransitions() {
		return transitions.values();
	}

	/**
	 * @return the places
	 */
	public Collection<RtPlace> getPlaces() {
		return places.values();
	}

}
