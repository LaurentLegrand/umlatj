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

/**
 * 
 */
package org.umlatj.internal.profile.behavior.basic;

import org.umlatj.internal.behavior.BehaviorBuilder;
import org.umlatj.internal.util.proxy.TypeProxyImpl;
import org.umlatj.profile.behavior.basic.Basic;



public class BasicBuilder extends BehaviorBuilder<KBasic> {

	public BasicBuilder() {
		super(Basic.class);
	}

	@Override
	public KBasic build(Class<?> type) {
		KBasic basic = super.build(type);

//		basic.setFirstAction(type.getAnnotation(Basic.class).value());
//
//		for (Method method : Classes.findMethods(type, Goto.class)) {
//			KGoto rtGoto = new KGoto();
//			rtGoto.setFrom(basic.getAction(method.getName()));
//			rtGoto.setTo(basic.getAction(method.getAnnotation(Goto.class).value()));
//			basic.addGoto(rtGoto);
//		}
//		
//		for (Method method : Classes.findMethods(type, If.class)) {
//			KGoto rtGoto = new KGoto();
//			rtGoto.setFrom(basic.getAction(method.getName()));
//			rtGoto.setTo(basic.getAction(method.getAnnotation(Goto.class).value()));
//			basic.addGoto(rtGoto);
//		}
		return basic;
	}

	public KBasic newBehavior(Class<?> type) {
		return new KBasic(new TypeProxyImpl(type));
	}

}