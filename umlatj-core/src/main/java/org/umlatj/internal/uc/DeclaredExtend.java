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

package org.umlatj.internal.uc;

import org.umlatj.internal.kernel.KConstraint;
import org.umlatj.internal.kernel.KNamedElement;
import org.umlatj.internal.util.proxy.MethodProxy;

public class DeclaredExtend extends KNamedElement {

	DeclaredUseCase useCase;

	MethodProxy<?> method;

	KConstraint condition;

	public DeclaredExtend(MethodProxy<?> method) {
		super(method.getName());
		this.method = method;
	}

	public Object newExtend(Object self) {
		if (this.condition != null && !this.condition.verify(self)) {
			return null;
		}
		return this.method.invoke(null, self);
	}

	/**
     * @return the useCase
     */
    public DeclaredUseCase getUseCase() {
    	return useCase;
    }

	/**
     * @param useCase the useCase to set
     */
    public void setUseCase(DeclaredUseCase useCase) {
    	this.useCase = useCase;
    }

	/**
     * @return the condition
     */
    public KConstraint getCondition() {
    	return condition;
    }

	/**
     * @param condition the condition to set
     */
    public void setCondition(KConstraint condition) {
    	this.condition = condition;
    }

}
