package ma.cigma.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Exemple5 {

	@Spy
	Properties spyProperties;
	// demonstrates of the spy function
	@Test
	public void testMockitoThrows() {

		doReturn("42").when(spyProperties).get("shoeSize");

		String value = (String) spyProperties.get("shoeSize");

		assertEquals("42", value);
	}

}
