package org.umlatj.kernel;


import org.junit.Assert;
import org.umlatj.internal.reflect.Types;
import org.umlatj.kernel.Association;
import org.umlatj.kernel.Classifier;
import org.umlatj.kernel.Package;
import org.umlatj.kernel.Property;
import org.umlatj.kernel.Association.Binary;
import org.umlatj.kernel.Association.End;

public class PackageTest {

	@org.junit.Test
	public void load() {
		Types.asPackage(Bar.class);
		// Assert.assertEquals(new MyPackage(), new MyPackage());
		Assert.assertNotNull(new Bar().getNextPrevious());
		Assert.assertNull(new Bar().nextPrevious);
	}

	@org.junit.Test
	public void create() {
		Foo test1 = new Foo();
		Foo test2 = new Foo();

		new Bar().getNextPrevious().add(test1, test2);
		Assert.assertEquals(test1.next, test2);
		Assert.assertEquals(test1, test2.previous);

	}

}

@Classifier
class Foo {

	@Property
	Foo next;

	@Property
	Foo previous;

}

@Package
class Bar {

	@Association( { @End("previous"), @End("next") })
	Binary<Foo, Foo> nextPrevious;

	public Binary<Foo, Foo> getNextPrevious() {
		return nextPrevious;
	}

}
