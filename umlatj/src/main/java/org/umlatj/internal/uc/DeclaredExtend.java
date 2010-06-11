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
