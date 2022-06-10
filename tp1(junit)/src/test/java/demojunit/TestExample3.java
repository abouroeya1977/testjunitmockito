package demojunit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TestExample3 {

	@Test
	void monPremierTest() {
		assertTrue(true);
		assertTrue(this::isValide);
		assertTrue(true, () -> "Description " + "du cas " + "de test");
		List<String> attendu = Arrays.asList("e1", "e2", "e2");
		List<String> actual = new LinkedList<>(attendu);
		assertEquals(attendu, actual);
		assertEquals(attendu, actual, "Les listes ne sont pas égales");
		assertEquals(attendu, actual, () -> "Les listes " + "ne sont " + "pas égales");
		assertNotSame(attendu, actual, "Les instances sont les memes");
	}

	boolean isValide() {
		return true;
	}
}
