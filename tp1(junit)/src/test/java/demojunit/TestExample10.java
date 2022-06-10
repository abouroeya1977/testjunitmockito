package demojunit;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TestExample10 {

	@Test
	  void monTest() {
	    fail("la raison de l'échec du test");
	  }
}
