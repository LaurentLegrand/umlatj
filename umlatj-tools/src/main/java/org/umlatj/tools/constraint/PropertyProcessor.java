package org.umlatj.tools.constraint;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

import org.umlatj.kernel.Property;

/**
 * Verify usage of {@link Property}
 * 
 * @author Laurent Legrand
 *
 */
public class PropertyProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		return false;
	}

}
