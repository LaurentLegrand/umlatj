package org.umlatj.kernel;

/**
 * AggregationKind is an enumeration type that specifies the literals for
 * defining the kind of aggregation of a property.
 * 
 * @author Laurent Legrand
 */
public enum AggregationKind {
	/**
	 * Indicates that the property has no aggregation.
	 */
	none,

	/**
	 * Indicates that the property has a shared aggregation.
	 */
	shared,

	/**
	 * Indicates that the property is aggregated compositely, i.e., the
	 * composite object has responsibility for the existence and storage of the
	 * composed objects (parts).
	 */
	composite;
}
