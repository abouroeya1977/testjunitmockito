package demojunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class TesttestRepete {
	@DisplayName("test addition rep�t�")
	@RepeatedTest(3)
	void testRepete() {
		Assertions.assertEquals(2, 1 + 1, "Valeur obtenue erron�e");
	}

	@DisplayName("test addition rep�t�")
	@RepeatedTest(value = 3, name = RepeatedTest.LONG_DISPLAY_NAME)
	void testRepete2() {
		Assertions.assertEquals(2, 1 + 1, "Valeur obtenue erron�e");
	}

}
