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

package org.umlatj.internal.profile.behavior.basic;

import org.umlatj.internal.behavior.KAction;
import org.umlatj.petri.OutputArc;
import org.umlatj.petri.Place;
import org.umlatj.petri.Transition;


public class KGoto extends OutputArc {

	public KGoto(Transition transition, Place place) {
		super(transition, place);
	}

	KAction from;

	KAction to;

	KBasic basic;

	/**
	 * @return the basic
	 */
	public KBasic getBasic() {
		return basic;
	}

	/**
	 * @param basic
	 *            the basic to set
	 */
	public void setBasic(KBasic basic) {
		this.basic = basic;
		//this.from.getPostTransition(), this, this.basic.getNet().getPlace(   this.to.getName()));
	}

	/**
	 * @return the from
	 */
	public KAction getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(KAction from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public KAction getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(KAction to) {
		this.to = to;
	}

}
