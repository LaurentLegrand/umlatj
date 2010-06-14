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

package org.umlatj.internal.util.proxy;

import java.lang.reflect.Field;


public class FieldProxyImpl<E> implements FieldProxy<E> {

	Field field;

	public FieldProxyImpl(Field field) {
		this.field = field;
		this.field.setAccessible(true);
	}
	
	//@Override
	public String getName() {
	    return this.field.getName();
	}

	@SuppressWarnings("unchecked")
    //@Override
	public E get(Object owner) {
		try {
			return (E) this.field.get(owner);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//@Override
	public void set(Object owner, E element) {
		try {
			this.field.set(owner, element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
