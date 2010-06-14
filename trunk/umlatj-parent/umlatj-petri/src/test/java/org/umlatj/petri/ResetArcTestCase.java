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


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.umlatj.petri.Net.Marking;

public class ResetArcTestCase {

	static Net net = new Net();

	static Place place = new Place(net, (short) 10);

	static Transition transition = new Transition(net);

	@BeforeClass
	public static void setup() {
		new ResetArc(place, transition);
	}

	Marking marking;

	@Before
	public void newMarking() {
		this.marking = net.newMarking(null);
	}

	@Test
	public void consumeOne() {
		Assert.assertTrue(transition.isEnabled(marking));
		transition.fire(marking);
		Assert.assertFalse(place.contains(marking));
		Assert.assertTrue(transition.isEnabled(marking));
	}

}
