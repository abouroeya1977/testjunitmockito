package demojunit;

import java.awt.Dimension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExample7 {
	@Test
	void verifierSame() {
		Object sut = new Dimension(800, 600);
		Object expected = new Dimension(800, 600);
		Assertions.assertSame(sut, expected);
	}

	@Test
	void verifierNotSame() {
		Object sut = new Dimension(800, 600);
		Object expected = sut;
		Assertions.assertNotSame(sut, expected);
	}
}
