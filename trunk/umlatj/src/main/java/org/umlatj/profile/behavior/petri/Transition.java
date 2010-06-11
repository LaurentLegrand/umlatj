package org.umlatj.profile.behavior.petri;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Transition {

	Arc[] inputArcs() default {};

	Arc[] ouptutArcs() default {};

	String[] reset() default {};
}
