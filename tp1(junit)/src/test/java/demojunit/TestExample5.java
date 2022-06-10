package demojunit;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExample5 {
	@Test
	void verifierLinesMatch() {
		List<String> expectedLines = Arrays.asList("A1", "A2", "A3", "A4");
		List<String> emails = Arrays.asList("A1", "A2", "A3", "A4");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	@Test
	void verifierLinesMatch2() {
		List<String> expectedLines = Arrays.asList("(.*)@(.*)", "(.*)@(.*)");
		List<String> emails = Arrays.asList("test@gmail.com", "jm@test.fr");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	/*
	 * Il est aussi possible d'ignorer un ou plusieurs éléments durant la
	 * comparaison grâce à un marqueur d'avance rapide : ils peuvent par exemple
	 * permettre d'ignorer des éléments dont la valeur change à chaque exécution.
	 * 
	 * Un marqueur d'avance rapide commence et termine par «>>» et doit posséder au
	 * moins quatre caractères.
	 */
	@Test
	void verifierLinesMatch3() {
		List<String> expectedLines = Arrays.asList("(.*)@(.*)", ">>>>", "(.*)@(.*)");
		List<String> emails = Arrays.asList("test@gmail.com", "test", "email", "jm@test.fr");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	/**
	 * Il est possible de mettre une description entre les doubles chevrons : cette
	 * description sera ignorée.
	 */

	@Test
	void verifierLinesMatch4() {
		List<String> expectedLines = Arrays.asList("(.*)@(.*)", ">> aller au dernier >>", "(.*)@(.*)");
		List<String> emails = Arrays.asList("test@gmail.com", "test", "email", "jm@test.fr");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	/**
	 * Il est possible de préciser un nombre exact d'éléments à ignorer.
	 * 
	 * 
	 */

	@Test
	void verifierLinesMatch5() {
		List<String> expectedLines = Arrays.asList("A1", ">> 2 >>", "A4");
		List<String> emails = Arrays.asList("A1", "A2", "A3", "A4");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	/**
	 * Si le nombre d'éléments à ignorer ne peut être atteint ou est insuffisant
	 * alors la méthode lève une exception.
	 */

	@Test
	void verifierLinesMatch6() {

		List<String> expectedLines = Arrays.asList("A1", ">> 1 >>", "A4");
		List<String> emails = Arrays.asList("A1", "A2", "A3", "A4");
		Assertions.assertLinesMatch(expectedLines, emails);
	}
}
