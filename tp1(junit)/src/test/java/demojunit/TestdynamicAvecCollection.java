package demojunit;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class TestdynamicAvecCollection {
	 @TestFactory
	  Collection<DynamicTest> dynamicTestsAvecCollection() {
	    Collection<DynamicTest> resultat = new ArrayList<>();
	    for (int i = 1; i <= 5; i++) {
	      int val = i;
	      resultat.add(DynamicTest.dynamicTest("Ajout " + val + "+" + val, 
	          () -> Assertions.assertEquals(val * 2, val + val)));
	    }
	    return resultat;
	  }
}
