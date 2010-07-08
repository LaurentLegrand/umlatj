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

/**
 * Represents an undirected link between a {@link Place} and a
 * {@link Transition}.
 * 
 * @author Laurent Legrand
 * 
 */
public abstract class Arc {

	/**
	 * The default {@link #weight} of an arc.
	 */
	public static final short DEFAULT_WEIGHT = 1;

	/**
	 * The transition involves in this link.
	 */
	private Transition transition;

	/**
	 * The place involves in this link.
	 */
	private Place place;

	/**
	 * The weight of the arc.
	 * 
	 * It corresponds to the number of tokens that will be consume / produce
	 * when the transition
	 * {@link Transition#fire(org.ollabaca.petri.Net.Marking) fires}.
	 */
	private short weight = DEFAULT_WEIGHT;

	/**
	 * Default constructor.
	 * 
	 */
	public Arc(Transition transition, Place place) {
		this(transition, place, DEFAULT_WEIGHT);
	}

	/**
	 * Default constructor.
	 * 
	 * @throws IllegalArgumentException
	 *             if weight is <= 0.
	 */
	public Arc(Transition transition, Place place, short weight) {
		if (weight <= 0) {
			throw new IllegalArgumentException("Weight must be greater than 0");
		}
		this.transition = transition;
		this.place = place;
		this.weight = weight;
	}

	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}

	/**
	 * @return the weight
	 */
	public short getWeight() {
		return weight;
	}

	/**
	 * @return the transition
	 */
	public Transition getTransition() {
		return transition;
	}

}
