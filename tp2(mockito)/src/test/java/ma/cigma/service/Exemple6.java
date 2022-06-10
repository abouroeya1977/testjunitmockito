package ma.cigma.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Exemple6 {
	@Mock
	OutputStream mockStream;
	@Test
	public void testForIOException() throws IOException {
		// create an configure mock
		doThrow(new IOException()).when(mockStream).close();

		// use mock
		OutputStreamWriter streamWriter = new OutputStreamWriter(mockStream);

		assertThrows(IOException.class, () -> streamWriter.close());
	}

}
