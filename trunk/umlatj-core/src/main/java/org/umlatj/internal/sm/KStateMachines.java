package org.umlatj.internal.sm;

import org.umlatj.kernel.Association;
import org.umlatj.kernel.Association.Binary;
import org.umlatj.kernel.Association.End;
import org.umlatj.kernel.Association.Hierarchy;
import org.umlatj.kernel.Package;

@Package
public class KStateMachines {

	@Association( { @End("parent"), @End("children") })
    private Hierarchy<KState> stateHierarchy;
	
	@Association( { @End("target"), @End("incoming") })
	private Binary<KTransition, KState> targetToIncoming;
	
	@Association( { @End("source"), @End("outgoing") })
	private Binary<KTransition, KState> sourceToOutgoing;
	
	public Hierarchy<KState> getStateHierarchy() {
		return stateHierarchy;
	}

	public Binary<KTransition, KState> getTargetToIncoming() {
		return targetToIncoming;
	}

	public Binary<KTransition, KState> getSourceToOutgoing() {
		return sourceToOutgoing;
	}
	
	
	
}
