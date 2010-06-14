package wiki;

import java.util.HashMap;
import java.util.Map;

import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Property;


@Classifier
public class Table {
	
	@Property(qualifier = "name")
	Map<String, Column> columns = new HashMap<String, Column>();
	
}
