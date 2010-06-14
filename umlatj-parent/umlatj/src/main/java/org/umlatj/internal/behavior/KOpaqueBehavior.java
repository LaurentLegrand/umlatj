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

package org.umlatj.internal.behavior;

import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.petri.InputArc;
import org.umlatj.petri.Place;


public class KOpaqueBehavior extends KBehavior {

	KConstraint preConditions;

	KConstraint postConditions;

	Place place;

	public KOpaqueBehavior(TypeProxy type) {
		super(type);
		this.place = new Place(this.net, (short) 1);
	}

	@Override
	public void addAction(KAction action) {
		super.addAction(action);
		new InputArc(this.place, action.getPreTransition());
	}

	protected void onComplete(KAction action, Object self) {
		BehaviorInstance instance = KBehavior.adapt(self);
		if (instance != null && instance.getOnCompletion() != null) {
			instance.getOnCompletion().run();
		}
	}

	public KConstraint getPreConditions() {
		return preConditions;
	}

	public void setPreConditions(KConstraint preConditions) {
		this.preConditions = preConditions;
	}

	public KConstraint getPostConditions() {
		return postConditions;
	}

	public void setPostConditions(KConstraint postConditions) {
		this.postConditions = postConditions;
	}

}
