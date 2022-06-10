package demojunit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.io.File;

import org.junit.jupiter.api.Test;

public class TestExample12 {

	@Test
	  void testSousWindows() {
	    System.out.println(System.getenv("OS"));
	    assumeTrue(System.getenv("OS").startsWith("Windows"));
	    assertTrue(false);
	  }
	
	 @Test
	  void testAvecSupposition() {
	    assumingThat(System.getenv("OS").startsWith("Windows"), () -> {
	      assertTrue(new File("C:/Windows").exists(), "Repertoire Windows inexistant");
	    });
	    assertTrue(true);
	  }
	 
	 public static void main(String[] args) {
		System.out.println(System.getenv("OS"));
	}
}
