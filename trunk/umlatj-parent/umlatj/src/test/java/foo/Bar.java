package foo;

import java.io.Closeable;
import java.io.IOException;

public class Bar implements Closeable {
	
	public Bar() {
		System.out.println(this);
	}

	//@Override
	public void close() throws IOException {
	}

}
