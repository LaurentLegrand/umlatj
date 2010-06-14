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

package org.umlatj.internal.behavior.petri;

public class RtArc {
	
	private RtPlace place;
	
	private short weight = 1;
	
	public RtArc() {
		
	}
	public RtArc(RtPlace place) {
	    this.place = place;
    }
	
	public RtArc(RtPlace place, short weight) {
	    this.place = place;
	    this.weight = weight;
    }
	

	/**
     * @return the place
     */
    public RtPlace getPlace() {
    	return place;
    }

	/**
     * @param place the place to set
     */
    public void setPlace(RtPlace place) {
    	this.place = place;
    }

	/**
     * @return the weight
     */
    public short getWeight() {
    	return weight;
    }

	/**
     * @param weight the weight to set
     */
    public void setWeight(short weight) {
    	this.weight = weight;
    }
	

}
