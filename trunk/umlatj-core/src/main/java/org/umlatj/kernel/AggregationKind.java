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

package org.umlatj.kernel;

/**
 * AggregationKind is an enumeration type that specifies the literals for
 * defining the kind of aggregation of a property.
 * 
 * @author Laurent Legrand
 */
public enum AggregationKind {
	/**
	 * Indicates that the property has no aggregation.
	 */
	none,

	/**
	 * Indicates that the property has a shared aggregation.
	 */
	shared,

	/**
	 * Indicates that the property is aggregated compositely, i.e., the
	 * composite object has responsibility for the existence and storage of the
	 * composed objects (parts).
	 */
	composite;
}
