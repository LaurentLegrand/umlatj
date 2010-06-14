package org.umlatj.internal.behavior.petri;

import org.umlatj.internal.behavior.petri.RtNet.NetInstance;

public class RtInputArc extends RtArc {

	public RtInputArc() {
	    super();
	    // TODO Auto-generated constructor stub
    }

	public RtInputArc(RtPlace place, short weight) {
	    super(place, weight);
	    // TODO Auto-generated constructor stub
    }

	public RtInputArc(RtPlace place) {
	    super(place);
	    // TODO Auto-generated constructor stub
    }

	public boolean canConsume(NetInstance instance) {
		return this.getPlace().size(instance) >= this.getWeight();
	}

	public void consume(NetInstance instance) {
		this.getPlace().consume(instance, this.getWeight());
	}

}
