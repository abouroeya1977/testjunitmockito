package demojunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestExample13 {
	@Test
	@Disabled("A écrire plus tard")
	void monTest() {
		Assertions.fail("Non implémenté");
	}
}
