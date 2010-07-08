package org.umlatj.uc;

import org.umlatj.uc.Extend;
import org.umlatj.uc.UseCase;

@UseCase
public class MyExtUseCase extends MyUseCase {

	@Extend(extension=MyUseCase.class, location="clone")
	public static MyUseCase myExtend(MyUseCase useCase) {
		return new MyExtUseCase();
	}

}
