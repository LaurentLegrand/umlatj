package org.umlatj.internal.sm;

import org.umlatj.kernel.Association;
import org.umlatj.kernel.Association.End;
import org.umlatj.kernel.Association.Hierarchy;
import org.umlatj.kernel.Package;

@Package
public class KStateMachines {

	@Association( { @End("parent"), @End("children") })
    private Hierarchy<KState> compositeStatesContainStates;
	
	
	public Hierarchy<KState> getCompositeStatesContainStates() {
		return compositeStatesContainStates;
	}
	
}
