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
 * This package contains an implementation of a Reset / Inhibitor Net.
 * 
 * From <em>Reduction Rules for Reset/Inhibitor Nets</em>
 * 
 * <h2>Abstract</h2>
 * <blockquote cite=
 * "http://is.tm.tue.nl/staff/wvdaalst/BPMcenter/reports/2007/BPM-07-13.pdf"> 
 * Reset/inhibitor nets are Petri nets extended with reset arcs and 
 * inhibitor arcs. A reset arc allows a transition to remove all tokens 
 * from a certain place when the transition fires. An inhibitor arc can 
 * stop a transition from being enabled if the place contains one or more
 * tokens.
 * </blockquote>
 * 
 * <h2>Implementation notes</h2>
 * <ul>
 * <li>A {@link org.umlatj.petri.Net.Marking marking} is represented by an array of short. 
 * Each cell of the array represents a place. And the value of that cell represents the number of
 * tokens in that place.</li>
 * <li>{@link Place} and {@link Transition} must have unique {@link Node#getId() id} within a
 * {@link Net} since their id are used as keys in {@link Map maps}.</li>
 * </ul>
 * 
 */
package org.umlatj.petri;

import java.util.Map;

