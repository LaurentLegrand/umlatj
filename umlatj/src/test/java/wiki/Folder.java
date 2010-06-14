package wiki;

import java.util.ArrayList;
import java.util.Collection;

import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Property;

@Classifier
public class Folder {

	@Property
	Collection<Document> documents = new ArrayList<Document>();

}
