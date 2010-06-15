package wiki;

import org.umlatj.kernel.Association;
import org.umlatj.kernel.Package;
import org.umlatj.kernel.Association.End;
import org.umlatj.kernel.Association.Hierarchy;

@Package
public class MyHierarchy {

	@Association( { @End("parent"), @End("subtasks") })
	private Hierarchy<Task> taskContainsSubtasks;

	public Hierarchy<Task> getTaskContainsSubtasks() {
		return taskContainsSubtasks;
	}

}
