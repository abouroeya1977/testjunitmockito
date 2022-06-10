package demojunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class TestCsvSource {
	@DisplayName("Addition")
	@ParameterizedTest()
	@CsvSource({ "1, 1", "1, 2", "2, 3" })
	void testAdditioner(int a, int b) {
		int attendu = a + b;
		Assertions.assertEquals(attendu, a + b);
	}
	
	 @ParameterizedTest()
	  @CsvFileSource(resources = "additionner_source.csv")
	  void testAdditionner(int a, int b) {
	    int attendu = a + b;
	    Assertions.assertEquals(attendu, a + b);
	  }
}
