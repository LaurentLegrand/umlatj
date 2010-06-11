package org.umlatj.internal.behavior.petri;

import org.umlatj.internal.behavior.petri.RtNet.NetInstance;

public class RtOutputArc extends RtArc {

	
	public RtOutputArc() {
	    super();
	    // TODO Auto-generated constructor stub
    }

	public RtOutputArc(RtPlace place, short weight) {
	    super(place, weight);
	    // TODO Auto-generated constructor stub
    }

	public RtOutputArc(RtPlace place) {
	    super(place);
	    // TODO Auto-generated constructor stub
    }

	public boolean canProduce(NetInstance instance) {
		return this.getPlace().size(instance) + this.getWeight() < this.getPlace().getCapacity();
	}

	public void produce(NetInstance instance) {
		this.getPlace().produce(instance, this.getWeight());
	}

}
