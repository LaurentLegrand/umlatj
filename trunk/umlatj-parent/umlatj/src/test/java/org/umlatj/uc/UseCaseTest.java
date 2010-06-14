package org.umlatj.uc;

import org.junit.Assert;
import org.junit.Test;

public class UseCaseTest {

	@Test
	public void test() {
		new MyExtUseCase();
		MyUseCase useCase = new MyUseCase();
		Assert.assertTrue(MyExtUseCase.class.isInstance(useCase.clone()));

	}

}
