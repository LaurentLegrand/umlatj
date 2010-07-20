package org.umlatj.tools.constraint;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import org.umlatj.kernel.Property;

/**
 * Verify usage of {@link Property}
 * 
 * @author Laurent Legrand
 * 
 */
@SupportedAnnotationTypes("org.umlatj.kernel.Property")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class PropertyProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		
		for (Element element: roundEnv.getElementsAnnotatedWith(Property.class)) {
			this.processingEnv.getMessager().printMessage(Kind.WARNING, "property found", element);
		}
		return false;
	}

}
