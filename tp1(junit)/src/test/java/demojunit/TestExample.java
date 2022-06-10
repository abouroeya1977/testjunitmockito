package demojunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Ma classe de test JUnit5")
public class TestExample {
	
	
	@Test
	@DisplayName("Mon cas de test")
	void test1() {
		System.out.println("simpleTest");
	    Assertions.assertTrue(true);
	}

}
