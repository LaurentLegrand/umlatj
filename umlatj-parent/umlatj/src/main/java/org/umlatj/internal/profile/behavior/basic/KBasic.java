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
import org.umlatj.internal.behavior.KBehavior;
import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.petri.InputArc;
import org.umlatj.petri.Place;


public class KBasic extends KBehavior {

	public KBasic(TypeProxy type) {
		super(type);
	}

	/**
	 * Initialize the input place of the first action.
	 */
	public void setFirstAction(String name) {
//		this.getNet().getPlace(name).setInitial((short) 1);
	}

	public void addGoto(KGoto declaredGoto) {
		declaredGoto.setBasic(this);
	}

	@Override
	public void addAction(KAction action) {
		super.addAction(action);
//		Place place = new Place(action.getName());
//		Petri.link(this.getNet(), place);
//		Petri.link(place, new InputArc(), action.getPreTransition());
	}

}
