package org.umlatj.kernel;

@Classifier
public class Issue5 {

	@Property(isDerivedUnion = true)
	public String getDerivedUnion() {
		return null;
	}

	@Property(subset = "derivedUnion")
	String subset;

}
