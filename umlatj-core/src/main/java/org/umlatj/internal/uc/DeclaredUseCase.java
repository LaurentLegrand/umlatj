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

import java.util.HashMap;
import java.util.Map;

import org.umlatj.internal.kernel.KType;
import org.umlatj.internal.util.proxy.TypeProxy;
import org.umlatj.internal.util.registry.Registry;


public class DeclaredUseCase extends KType {

	Map<String, DeclaredExtensionPoint> extensionPoints = new HashMap<String, DeclaredExtensionPoint>();

	Map<String, DeclaredExtend> extend = new HashMap<String, DeclaredExtend>();

	public DeclaredUseCase(TypeProxy type) {
		super(type);
	}

	public void addExtensionPoint(DeclaredExtensionPoint extensionPoint) {
		this.extensionPoints.put(extensionPoint.getName(), extensionPoint);
		extensionPoint.setUseCase(this);
	}

	public DeclaredExtensionPoint getExtensionPoint(String name) {
		return this.extensionPoints.get(name);
	}

	public void addExtend(DeclaredExtend extend) {
		this.extend.put(extend.getName(), extend);
		extend.setUseCase(this);
	}

	public DeclaredExtend getExtend(String name) {
		return this.extend.get(name);
	}
	
	static Registry<DeclaredUseCase> registry = new Registry<DeclaredUseCase>();

	public static DeclaredUseCase get(Class<?> type) {
		return registry.get(type);
	}
}
