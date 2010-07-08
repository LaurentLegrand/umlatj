package wiki;

import org.junit.Assert;
import org.junit.Test;

public class AssociationTest {

	@Test
	public void test() {
		Folder folder = new Folder();
		
		Document document = new Document();
		new MyPackage().getFolderContainsDocuments().add(folder, document);
		
		Assert.assertEquals(document.folder, folder);
		Assert.assertTrue(folder.documents.contains(document));
		
	}
}
