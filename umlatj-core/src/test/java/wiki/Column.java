package wiki;

import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Property;

@Classifier
public class Column {

	@Property
	private String name;
	
	@Property
	Table table;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
