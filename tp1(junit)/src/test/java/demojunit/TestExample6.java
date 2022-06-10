package demojunit;

import java.awt.Dimension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExample6 {
	 @Test
	  void verifierNull() {
	    Object sut = new Dimension(800, 600);
	    Assertions.assertNull(sut);
	  }
	 
	 @Test
	  void verifierNotNull() {
	    Object sut = null;
	    Assertions.assertNotNull(sut);
	  }
}
