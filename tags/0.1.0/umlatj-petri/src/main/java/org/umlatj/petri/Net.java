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

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a reset / inhibitor Net.
 * 
 * 
 * @author Laurent Legrand
 */
public class Net {

	/**
	 * A representation of a marking of this {@link Net}.
	 * 
	 * @author Laurent Legrand
	 * 
	 */
	public class Marking {

		/**
		 * A context that might be linked with this marking.
		 * 
		 * @see Net#newMarking(Object)
		 */
		public final Object context;

		/**
		 * The real marking where tokens are represented as {@link Short}.
		 */
		short[] marking;

		/**
		 * Constructor
		 * 
		 * @param context
		 *            a context that might be linked with this marking.
		 */
		Marking(Object context) {
			this.context = context;
			Net.this.init(this);
		}

		/**
		 * Return the {@link Net}.
		 * 
		 * @return
		 */
		public Net getNet() {
			return Net.this;
		}

	}

	/**
	 * Contains the transitions belonging to this net.
	 * 
	 * The key of the map is the name of the transition.
	 */
	private Set<Transition> transitions = new HashSet<Transition>();

	/**
	 * Contains the places belonging to this net.
	 * 
	 * The key of the map is the name of the place.
	 */
	private Set<Place> places = new HashSet<Place>();

	public Net() {
	}

	/**
	 * Initialize the marking to its initial state.
	 * 
	 * @param marking
	 */
	public void init(Marking marking) {
		if (marking.marking == null) {
			marking.marking = new short[this.places.size()];
		}
		for (Place place : this.places) {
			place.init(marking);
		}
	}

	/**
	 * Create a new marking for this net.
	 * 
	 * @param context
	 *            an optional context linked to that marking.
	 * @return a new marking.
	 */
	public Marking newMarking(Object context) {
		return new Marking(context);
	}

	/**
	 * Verify the liveness of the marking.
	 * 
	 * Return
	 * 
	 * @return <code>true</code> if the firing sequence is empty.
	 *         <code>false</code> if there is at least one enabled transition.
	 */
	public boolean isDead(Marking marking) {
		for (Transition transition : this.transitions) {
			if (transition.isEnabled(marking)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Add a transition to this net.
	 * 
	 * @param transition
	 *            the transition to add.
	 */
	void addTransition(Transition transition) {
		this.transitions.add(transition);
	}

	/**
	 * Add a place to the net.
	 * 
	 * @param place
	 *            the place to add.
	 */
	void addPlace(Place place) {
		place.setIndex(this.places.size());
		this.places.add(place);
	}
}
