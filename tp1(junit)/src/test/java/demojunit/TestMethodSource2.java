package demojunit;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestMethodSource2 {
	 @ParameterizedTest
	  @MethodSource("fournirDonnees")
	  void testTraiter(int index, String element) {
	    Assertions.assertTrue(index > 0);
	    Assertions.assertTrue(element.startsWith("elem"));
	  }
	 
	  static List<Object[]> fournirDonnees() {
	    return Arrays.asList(new Object[][] { { 1, "elem1" }, { 2, "elem2" } });
	  }
}
