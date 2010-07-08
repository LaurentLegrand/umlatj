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
 * Represents a transition in the net.
 * 
 * <p>
 * When a transition fires, it consumes tokens from input places, it produces
 * tokens into output places and reset places.
 * </p>
 * 
 * @author Laurent Legrand
 * 
 * @version $Id$
 */
public class Transition extends Node {

	public Transition(Net net) {
		super(net);
		net.addTransition(this);
	}

	/**
	 * Verify is this transition is enabled.
	 * 
	 * @param marking
	 *            the instance containing the marking.
	 * @return <code>true</code> if this transition is enabled.
	 */
	public boolean isEnabled(Marking marking) {
		// verify that input places contains enough tokens
		for (InputArc arc : this.inputArcs) {
			if (!arc.canConsume(marking)) {
				return false;
			}
		}
		// verify that output places can contain tokens that will be produced
		for (OutputArc arc : this.outputArcs) {
			if (!arc.canProduce(marking)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Run the pre-fire sequence: consuming tokens from input places.
	 * 
	 * Note. the transition enabling is not verified.
	 * 
	 * @param instance
	 *            the instance containing the marking.
	 * @throws IllegalStateException
	 */
	protected void preFire(Marking marking) throws IllegalStateException {
		for (InputArc arc : this.inputArcs) {
			arc.consume(marking);
		}
	}

	/**
	 * Run the post-fire sequence: producing tokens into output places and reset
	 * places.
	 * 
	 * @param instance
	 *            the instance containing the marking.
	 */
	protected void postFire(Marking marking) {
		for (OutputArc arc : this.outputArcs) {
			arc.produce(marking);
		}
	}

	/**
	 * Fire the transition: verify the enabling of the transition, pre-fire and
	 * post-fire.
	 * 
	 * @param instance
	 *            the instance containing the marking.
	 * 
	 * @throws IllegalStateException
	 */
	public void fire(Marking marking) {
		if (!this.isEnabled(marking)) {
			// TODO message
			throw new IllegalStateException("Pre conditions not met");
		}
		this.preFire(marking);
		this.postFire(marking);
	}

}
