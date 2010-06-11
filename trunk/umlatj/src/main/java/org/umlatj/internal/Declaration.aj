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
