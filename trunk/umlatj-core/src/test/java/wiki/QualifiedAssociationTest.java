package wiki;

import org.junit.Assert;
import org.junit.Test;

public class QualifiedAssociationTest {
	
	@Test
	public void test() {
		Table table = new Table();
		Column column = new Column();
		column.setName("col");
		
		// create links 
		new QualifiedAssociation().getTableContainsColumns().add(table, column);
		
		Assert.assertTrue(table.columns.containsKey(column.getName()));
		Assert.assertEquals(table.columns.get(column.getName()), column);
	}

}
