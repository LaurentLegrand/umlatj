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

package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.util.proxy.MethodProxy;

/**
 * Constraint corresponding to a static method with one parameter.
 * 
 * 
 * @author Laurent Legrand
 * 
 * @param <O>
 */
public class TargetConstraint extends KConstraint {

	MethodProxy<Boolean> method;

	public TargetConstraint(MethodProxy<Boolean> method) {
		super(method.getName());
		this.method = method;
	}

	@Override
	public boolean verify(Object self) {
		Boolean value = this.method.invoke(null, self);
		// to prevent NPE during auto boxing
		return (value == null) ? false : value;
	}

}
