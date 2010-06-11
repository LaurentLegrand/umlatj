package org.umlatj.kernel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A classifier is a classification of instances, it describes a set of
 * instances that have features in common.
 * 
 * A classifier is a namespace whose members can include features. Classifier is
 * an abstract metaclass.
 * 
 * A classifier is a type and can own generalizations, thereby making it
 * possible to define generalization relationships to other classifiers. A
 * classifier can specify a generalization hierarchy by referencing its general
 * classifiers.
 * 
 * A classifier is a redefinable element, meaning that it is possible to
 * redefine nested classifiers.
 * 
 * @author Laurent Legrand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Classifier {

}
