package org.umlatj.uc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An extension point identifies a point in the behavior of a use case where
 * that behavior can be extended by the behavior of some other (extending) use
 * case, as specified by an extend relationship.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtensionPoint {

}
