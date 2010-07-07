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
package org.umlatj.internal.behavior.petri;

import java.util.UUID;

import org.umlatj.internal.behavior.petri.RtNet.NetInstance;
import org.umlatj.internal.kernel.KNamedElement;


public class RtPlace extends KNamedElement {
	
	/**
	 * The position of this place in the marking.
	 */
	int index;

	short initial = 0;

	short capacity = Short.MAX_VALUE;

	public RtPlace() {
		super(UUID.randomUUID().toString());
	}
	
	public RtPlace(String name) {
		super(name);
	}
	
	public RtPlace(String name, short initial) {
	    super(name);
	    this.initial = initial;
    }
	
	public RtPlace(String name, short initial, short capacity) {
	    super(name);
	    this.initial = initial;
	    this.capacity = capacity;
    }
	

	void init(NetInstance instance) {
		instance.marking[this.index] = initial;
	}

	/**
	 * Verify if this place contains tokens.
	 * 
	 * @return <code>true</code> if there is at least one token.
	 *         <code>false</code> otherwise.
	 */
	public boolean contains(NetInstance instance) {
		return instance.marking[this.index] > 0;
	}

	/**
	 * Consume a token from this initial.
	 */
	public void consume(NetInstance instance) {
		instance.marking[this.index]--;
	}

	/**
	 * Consume tokens from this initial.
	 */
	public void consume(NetInstance instance, short tokens) {
		instance.marking[this.index] -= tokens;
	}

	/**
	 * Produce a token into the initial.
	 */
	public void produce(NetInstance instance) {
		instance.marking[this.index]++;
	}

	/**
	 * Produce n token into the initial.
	 */
	public void produce(NetInstance instance, short tokens) {
		instance.marking[this.index] += tokens;
	}

	/**
	 * Remove all tokens from this place.
	 * 
	 */
	public void reset(NetInstance instance) {
		instance.marking[this.index] = 0;
	}

	/**
	 * Return the number of tokens in this place.
	 * 
	 * @return
	 */
	public int size(NetInstance instance) {
		return instance.marking[this.index];
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