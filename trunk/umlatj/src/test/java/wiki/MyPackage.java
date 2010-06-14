package wiki;

import org.umlatj.kernel.Association;
import org.umlatj.kernel.Package;
import org.umlatj.kernel.Association.Binary;
import org.umlatj.kernel.Association.End;

@Package
public class MyPackage {

	@Association( { @End("folder"), @End("documents") })
	private Binary<Folder, Document> folderContainsDocuments;

	public Binary<Folder, Document> getFolderContainsDocuments() {
		return folderContainsDocuments;
	}
}
