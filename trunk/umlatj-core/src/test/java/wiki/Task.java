package wiki;

import java.util.ArrayList;
import java.util.List;

import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Property;

@Classifier
public class Task {
	
	@Property
	Task parent;
	
	@Property
	List<Task> subtasks = new ArrayList<Task>();

}
