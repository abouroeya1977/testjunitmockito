package demojunit;

import java.time.Month;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestParametre {
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void testParametreAvecValueSource(int valeur) {
		Assertions.assertEquals(valeur + valeur, valeur * 2);
	}
	
	  @ParameterizedTest
	  @EnumSource(Month.class)
	  void testParametreAvecEnumSource(Month mois) {
	    System.out.println(mois);
	    Assertions.assertNotNull(mois);
	  }
}
