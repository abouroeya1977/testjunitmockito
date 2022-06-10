package demojunit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TestExample8 {
	@Test
	void verifierException() {

		String valeur = null;
		assertThrows(NumberFormatException.class, () -> {
			Integer.valueOf(valeur);
		});
	}

	@Test
	void verifierException2() {
		String valeur = "1";
		assertThrows(NumberFormatException.class, () -> {
			Integer.valueOf(valeur);
		});
	}
	
	public void maMethode() {
	    throw new RuntimeException("mon message d'erreur");
	  }
	
	@Test
	  void verifierException3() {
	    TestExample8 sut = new TestExample8();
	    RuntimeException excep = assertThrows(RuntimeException.class, sut::maMethode);
	    assertAll(() -> assertEquals("message erreur", excep.getMessage()), 
	              () -> assertNull(excep.getCause()));
	  }
}
