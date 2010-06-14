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