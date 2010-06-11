package org.umlatj.kernel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A data type is a type whose instances are identified only by their value. A
 * DataType may contain attributes to support the modeling of structured data
 * types.
 * 
 * A typical use of data types would be to represent programming language
 * primitive types or CORBA basic types. For example, integer and string types
 * are often treated as data types.
 * 
 * 
 * <h1>Semantics</h1>
 * 
 * A data type is a special kind of classifier, similar to a class. It differs
 * from a class in that instances of a data type are identified only by their
 * value.
 * 
 * All copies of an instance of a data type and any instances of that data type
 * with the same value are considered to be the same instance. Instances of a
 * data type that have attributes (i.e., is a structured data type) are
 * considered to be the same if the structure is the same and the values of the
 * corresponding attributes are the same. If a data type has attributes, then
 * instances of that data type will contain attribute values matching the
 * attributes.
 * 
 * @author Laurent Legrand
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataType {

}
