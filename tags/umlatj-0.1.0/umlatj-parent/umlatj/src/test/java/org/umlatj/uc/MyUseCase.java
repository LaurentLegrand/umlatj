package org.umlatj.uc;

import org.umlatj.uc.ExtensionPoint;
import org.umlatj.uc.UseCase;

@UseCase
public class MyUseCase {

	@ExtensionPoint
	public MyUseCase clone() {
		return new MyUseCase();
	}

}
