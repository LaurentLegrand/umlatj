package wiki;

import org.umlatj.kernel.Association;
import org.umlatj.kernel.Package;
import org.umlatj.kernel.Association.Binary;
import org.umlatj.kernel.Association.End;

@Package
public class QualifiedAssociation {

	@Association( { @End("table"), @End("columns") })
	Binary<Table, Column> tableContainsColumns;

	public Binary<Table, Column> getTableContainsColumns() {
		return tableContainsColumns;
	}

}
