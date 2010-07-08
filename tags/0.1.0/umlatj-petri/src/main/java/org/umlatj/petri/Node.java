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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A {@link Node} represents an element of a {@link Net}.
 * 
 * @author Laurent Legrand
 * 
 */
public class Node {

	/**
	 * The Net that owns this node.
	 */
	private Net net;

	/**
	 * An optional name.
	 */
	private String name;

	/**
	 * The set of input arcs.
	 */
	protected Set<InputArc> inputArcs = new HashSet<InputArc>();

	/**
	 * The set of output arcs.
	 */
	protected Set<OutputArc> outputArcs = new HashSet<OutputArc>();

	/**
	 * Create a node with a generated id based on {@link UUID}.
	 * 
	 */
	public Node(Net net) {
		this.net = net;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Add an input arc.
	 * 
	 * @param inputArc
	 */
	void addInputArc(InputArc inputArc) {
		this.inputArcs.add(inputArc);
	}

	/**
	 * Add an output arc.
	 * 
	 * @param inputArc
	 */
	void addOutputArc(OutputArc outputArc) {
		this.outputArcs.add(outputArc);
	}

	public Net getNet() {
		return net;
	}

}
