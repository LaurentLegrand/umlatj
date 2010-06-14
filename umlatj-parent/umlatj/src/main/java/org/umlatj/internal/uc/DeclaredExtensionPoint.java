package org.umlatj.internal.uc;

import org.umlatj.internal.kernel.KNamedElement;
import org.umlatj.internal.util.proxy.MethodProxy;

public class DeclaredExtensionPoint extends KNamedElement {

	DeclaredUseCase useCase;

	MethodProxy<?> method;

	DeclaredExtend extend;

	public DeclaredExtensionPoint(MethodProxy<?> method) {
		super(method.getName());
		this.method = method;
	}
	
	public Object newExtend(Object self) {
		return (this.extend != null) ? this.extend.newExtend(self) : null;
	}
	

	/**
	 * @return the useCase
	 */
	public DeclaredUseCase getUseCase() {
		return useCase;
	}

	/**
	 * @param useCase
	 *            the useCase to set
	 */
	public void setUseCase(DeclaredUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * @return the extend
	 */
	public DeclaredExtend getExtend() {
		return extend;
	}

	/**
	 * @param extend
	 *            the extend to set
	 */
	public void setExtend(DeclaredExtend extend) {
		this.extend = extend;
	}

}
