package demojunit;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExample9 {

	/**
	 * Les assertions assertTimeout et assertTimeoutPreemptively vérifie que les
	 * traitements fournis en paramètre s'exécutent avant le délai précisé. La
	 * différence entre les deux est que assertTimeoutPreemptively interrompt
	 * l'exécution des traitements si le délai est dépassé.
	 */
	@Test
	void verifierTimeout() {
		Assertions.assertTimeout(Duration.ofMillis(200), () -> {
			return "";
		});
		Assertions.assertTimeout(Duration.ofSeconds(1), TestExample9::traiter);
	}

	private static String traiter() throws InterruptedException {
		Thread.sleep(2000);
		return "";
	}

	@Test
	void verifierTimeoutPreemptively() {
		Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
			return "";
		});

		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), TestExample9::traiter);
	}
}
