package foo;

import java.io.Closeable;
import java.util.Iterator;

import javax.imageio.spi.ServiceRegistry;

import org.junit.Test;


public class BarTest {
	
	@Test
	public void test() {
		Iterator<Closeable> iterator = ServiceRegistry.lookupProviders(Closeable.class);
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
