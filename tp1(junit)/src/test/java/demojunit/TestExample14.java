package demojunit;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("principal")
public class TestExample14 {
	@Test
	@Tag("general")
	void testCas1() {
		assertTrue(true);
	}

	@Test
	@Tag("specifique")
	void testCas2() {
		assertTrue(true);
	}

}
