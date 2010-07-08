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
 * From <em>Reduction Rules for Reset/Inhibitor Nets</em>
 * 
 * <blockquote cite=
 * "http://is.tm.tue.nl/staff/wvdaalst/BPMcenter/reports/2007/BPM-07-13.pdf"> An
 * inhibitor arc is a type of arc that goes from a {@link Place} to a
 * {@link Transition} and its semantics is to prevent the transition from firing
 * when the place contains one or more tokens. </blockquote>
 * 
 * @author Laurent Legrand
 * 
 */
public class InhibitorArc extends InputArc {

	public InhibitorArc(Place place, Transition transition) {
		super(place, transition);
	}

	/**
	 * Return the negation of the {@link InputArc#canConsume(Marking)}.
	 * 
	 */
	@Override
	public boolean canConsume(Marking marking) {
		return !super.canConsume(marking);
	}

	/**
	 * Do nothing.
	 * 
	 */
	@Override
	public void consume(Marking marking) {
	}

}
