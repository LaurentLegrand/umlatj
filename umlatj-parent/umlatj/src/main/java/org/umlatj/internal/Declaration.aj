/**
 * uml@J, UML annotations for Java
 *
 * Copyright (C) 2010 Laurent Legrand or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

package org.umlatj.internal;

import org.umlatj.behavior.*;
import org.umlatj.internal.kernel.KNamedElement;
import org.umlatj.internal.util.aop.Advice;
import org.umlatj.kernel.*;


//import javax.ejb.*;


/**
 * This aspect defines the annotation declaration.
 * 
 * @author Laurent Legrand
 *
 */
public aspect Declaration {
	
	/**
	 * An activity is a classifier
	 * 
	 */
	declare @type: @Activity *: @Classifier;
	
	/**
	 * An opaque behavior is a classifier
	 * 
	 */
	declare @type: @OpaqueBehavior *: @Classifier;
	
	/**
	 * A package is a singleton
	 * 
	 */
	//declare @type: @Package *: @Singleton;
	
	/**
	 * An action of an activity defines a default transaction 
	 * attributes. 
	 * It allows to manage transparently the transaction context.
	 */
//	declare @method: @Action * *.*(): @TransactionAttribute;
	
	/*declare @method: * (@OpaqueBehavior *).run(..): @Action;

	declare @method: * (@OpaqueBehavior *).call(..): @Action;*/

	//declare precedence: org.umlatj.rt.util.*, org.umlatj.rt.reflect.*; 

	//declare precedence: org.umlatj.rt.reflect.kernel.ReflectClassifiers, org.umlatj.rt.reflect.behavior.ReflectActivities;
		
	after(KNamedElement self) : execution(KNamedElement.new(..)) && this(self) {
		System.out.println("new: " + self.getClass() + ": " + self.getName());
	}
	
	before(Advice advice): execution(* Advice.*(..)) && this(advice) {
		System.out.println(advice + ": before: ");
	}
}
