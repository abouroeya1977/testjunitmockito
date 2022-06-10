package ma.cigma.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Exemple12 {
	@Test
    void shouldMockStaticMethod() {
        try (MockedStatic<Utility> mockedStatic = Mockito.mockStatic(Utility.class)) {

            mockedStatic.when(() -> Utility.getDatabaseConnection(eq("test"))).thenReturn("testing");
            mockedStatic.when(() -> Utility.getDatabaseConnection(eq("prod"))).thenReturn("production");

            String result1 = Utility.getDatabaseConnection("test");

            assertEquals("testing", result1);
            String result2 = Utility.getDatabaseConnection("prod");
            assertEquals("production", result2);

        }

    }
}
