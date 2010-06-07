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

/**
 * 
 */
package org.umlatj.petri;

import org.umlatj.petri.Net.Marking;

/**
 * Represents a place in a Petri Net.
 * 
 * 
 * 
 * @author Laurent Legrand
 * 
 */
public class Place extends Node {

	/**
	 * The position of this place in the marking.
	 */
	private int index;

	/**
	 * The initial number of tokens in this place.
	 * 
	 * Default value is <code>0</code>.
	 */
	private short initial = 0;

	/**
	 * The maximum number of tokens in this place.
	 * 
	 * Default value is {@link Short#MAX_VALUE}.
	 */
	private short capacity = Short.MAX_VALUE;

	public Place(Net net) {
		this(net, (short)0, Short.MAX_VALUE);
	}

	public Place(Net net, short initial) {
		this(net, initial, Short.MAX_VALUE);
	}

	public Place(Net net, short initial, short capacity) {
		super(net);
		net.addPlace(this);
		this.initial = initial;
		this.capacity = capacity;
	}

	/**
	 * Verify if this place contains tokens.
	 * 
	 * @return <code>true</code> if there is at least one token.
	 *         <code>false</code> otherwise.
	 */
	public boolean contains(Marking marking) {
		return marking.marking[this.index] > 0;
	}

	/**
	 * Consume a token from this marking.
	 */
	public void consume(Marking marking) {
		marking.marking[this.index]--;
	}

	/**
	 * Consume tokens from this marking.
	 */
	public void consume(Marking marking, short tokens) {
		marking.marking[this.index] -= tokens;
	}

	/**
	 * Produce a token into the marking.
	 */
	public void produce(Marking marking) {
		marking.marking[this.index]++;
	}

	/**
	 * Produce n token into the marking.
	 */
	public void produce(Marking marking, short tokens) {
		marking.marking[this.index] += tokens;
	}

	/**
	 * Initialize the marking to the initial number of tokens.
	 * 
	 */
	public void init(Marking marking) {
		marking.marking[this.index] = initial;
	}

	/**
	 * Reset the marking: remove all tokens.
	 * 
	 */
	public void reset(Marking marking) {
		marking.marking[this.index] = 0;
	}

	/**
	 * Return the number of tokens in this place.
	 * 
	 * @return
	 */
	public int size(Marking marking) {
		return marking.marking[this.index];
	}

	/**
	 * @return the capacity
	 */
	public short getCapacity() {
		return this.capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(short capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the initial
	 */
	public short getInitial() {
		return this.initial;
	}

	/**
	 * @param initial
	 *            the initial to set
	 */
	public void setInitial(short value) {
		this.initial = value;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

}