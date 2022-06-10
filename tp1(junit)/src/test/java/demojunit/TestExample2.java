package demojunit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestExample2 {
	@BeforeAll
	  static void initAll() {
	    System.out.println("beforeAll");
	  }
	 
	  @BeforeEach
	  void init() {
	    System.out.println("beforeEach");
	  }
	 
	  @AfterEach
	  void tearDown() {
	    System.out.println("afterEach");
	  }
	 
	  @AfterAll
	  static void tearDownAll() {
	    System.out.println("afterAll");
	  }
	 
	  @Test
	  void simpleTest() {
	    System.out.println("simpleTest");
	    Assertions.assertTrue(true);
	  }
	 
	  @Test 
	  void secondTest() {
	    System.out.println("secondTest");
	    Assertions.assertTrue(true);
	  }

}
