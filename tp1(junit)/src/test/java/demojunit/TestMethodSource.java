package demojunit;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestMethodSource {
	@ParameterizedTest
	@MethodSource("fournirDonnees")
	void testExecuter(String element) {
		Assertions.assertTrue(element.startsWith("elem"));
	}

	static Stream<String> fournirDonnees() {
		return Stream.of("elem1", "elem2","hh");
	}
}
