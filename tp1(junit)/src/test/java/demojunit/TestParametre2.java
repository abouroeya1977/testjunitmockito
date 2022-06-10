package demojunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestParametre2 {
	 @DisplayName("Addition")
	  @ParameterizedTest(name = "{index} : l''addition de {0} et {1}")
	  @CsvSource({ "1, 1", "1, 2", "2, 3" })
	  void testAdditioner(int a, int b) {
	    int attendu = a + b;
	    Assertions.assertEquals(attendu, a + b);
	  }
}
