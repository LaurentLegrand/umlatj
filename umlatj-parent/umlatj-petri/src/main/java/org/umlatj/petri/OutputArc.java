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

package org.umlatj.petri;

import org.umlatj.petri.Net.Marking;

/**
 * A directed link that goes from a {@link Transition} to a {@link Place}.
 * 
 * When the transition fires, tokens will be produced into the place.
 * 
 * @author Laurent Legrand
 * 
 */

public class OutputArc extends Arc {

	public OutputArc(Transition transition, Place place, short weight) {
		super(transition, place, weight);
		transition.addOutputArc(this);
		transition.addOutputArc(this);
	}

	public OutputArc(Transition transition, Place place) {
		super(transition, place);
		transition.addOutputArc(this);
		transition.addOutputArc(this);
	}

	/**
	 * Verify that {@link #getWeight()} token(s) can be produced into the
	 * {@link #getPlace()}.
	 * 
	 * @param marking
	 * @return
	 */
	public boolean canProduce(Marking marking) {
		return this.getPlace().size(marking) + this.getWeight() < this
				.getPlace().getCapacity();
	}

	/**
	 * Produce {@link #getWeight()} token(s) into the {@link #getPlace()}.
	 * 
	 * @param marking
	 * @return
	 */
	public void produce(Marking marking) {
		this.getPlace().produce(marking, this.getWeight());
	}

}
