package wiki;

import org.junit.Assert;
import org.junit.Test;

public class HierarchyTest {

	@Test
	public void test() {

		Task task = new Task();
		Task task_1 = new Task();
		Task task_1_1 = new Task();
		Task task_1_2 = new Task();

		MyHierarchy hierarchy = new MyHierarchy();
		hierarchy.getTaskContainsSubtasks().add(task, task_1);
		hierarchy.getTaskContainsSubtasks().add(task_1, task_1_1);
		hierarchy.getTaskContainsSubtasks().add(task_1, task_1_2);

		// check root
		Assert.assertEquals(hierarchy.getTaskContainsSubtasks().getRoot(
				task_1_2), task);

		// get the ancestor axis
		Assert.assertTrue(hierarchy.getTaskContainsSubtasks().getAncestors(
				task_1_2).contains(task_1));

	}

}
